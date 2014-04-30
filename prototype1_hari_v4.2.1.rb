require 'csv'
require 'test/unit'
require 'optparse'
extend Test::Unit::Assertions

$logfile = File.new("log.txt", "w")
s = nil
params = nil
start_time = nil
first_line = nil

def read_csv(parameter_file)
  if (File.file?(parameter_file))
    $s = IO.read(parameter_file)
    csv = CSV.new($s, {:headers => true, :header_converters => :symbol})
    return csv.to_a.map {|row| row.to_hash }
  else
    $logfile.write("Cannot read #{parameter_file}\n\n")
    total_time
    exit
  end
end

def read_params(parameter_file)
  return read_csv(parameter_file)
  $first_line = $s.split("\n")[0]
  if ($first_line.eql?("n,TH,i,o"))
  else
    $logfile.write("Wrong CSV format for input file #{parameter_file} - wrong headers.\n\n")
    total_time
    exit
  end
end

def read_original(sirca_file)
  entries = read_csv(sirca_file) 
  $first_line = $s.split("\n")[0]
  if ($first_line.eql?("#Instrument,Date,Time,Record Type,Price,Volume,Undisclosed Volume,Value,Qualifiers,Trans ID,Bid ID,Ask ID,Bid/Ask,Entry Time,Old Price,Old Volume,Buyer Broker ID,Seller Broker ID"))
    all_trades = []
    counter = 0
    entries.each do |entry|
      if (entry[:record_type] == "TRADE")
        all_trades << entry
      end
    end
  else
    $logfile.write("Wrong CSV format for input file #{sirca_file} - wrong headers.\n\n")
    total_time
    exit
  end
  
  $logfile.write("Number of total records: #{entries.size}\n")
  $logfile.write("Number of total TRADES: #{all_trades.size}\n\n")
  
  return all_trades
end
  
def find_returns(all_trades)
  prices = []
  #ensure all_trades is ordered by time
  all_trades.each do |trade|
    p = trade[:price].to_f
    prices << p
    #ensure it is done in chronological order
  end

  assert_equal(all_trades.size, prices.size, "first failed")
  $logfile.write("Prices gathered.\n")
  
  returns_array = []
  returns_array << nil
  prices.length.times do |i|
    if (i != 0)
      init_p = prices[i-1].to_f
      final_p = prices[i].to_f
      ret = (final_p - init_p)/init_p
      
      returns_array << ret.to_f
    end
  end

  assert_equal(all_trades.size, returns_array.size, "second failed")
  $logfile.write("Returns gathered.\n\n")
  
  return returns_array
end
  
def find_SMAs(n, returns)
  sma_array = []
  (n-1).times do |i|
    sma_array << nil
    print nil
  end
  
  (returns.size-(n-1)).times do |j|
    if (returns[j] != nil)
      sum = 0.to_f
      (n-1).times do |k|
	sum = sum + returns[(j+k)].to_f
      end
    
      sma = (sum/n).to_f
      sma_array << sma
    else
      sma_array << nil
    end
  end
  
  assert_equal(sma_array.size, returns.size)
  $logfile.write("SMAs gathered.\n\n")
  
  return sma_array
end
  
def create_transactions(thres, sma_array)
  threshold = thres.abs

  tsv_array = []
  tsv_array << nil
  
  (sma_array.size-1).times do |i|
    if(sma_array[i] != nil)
      tsv = sma_array[i+1].to_f - sma_array[i].to_f
      tsv_array << tsv
    else
      tsv_array << nil
    end
  end
  
  ts_array = []
  num_ts = tsv_array.size
  
  tsv_array.size.times do |j|
    if (tsv_array[j] != nil)
      if(tsv_array[j].to_f > threshold)
	ts_array << 'B'
      elsif(tsv_array[j].to_f < (-threshold))
	ts_array << 'A'
      else
	ts_array << 'N'
	num_ts = num_ts - 1
      end 
    else
      ts_array << nil
      num_ts = num_ts - 1
    end
    
  end
  
  assert_equal(sma_array.size, ts_array.size)
  $logfile.write("#{num_ts} ENTER transactions gathered.\n\n")
  
  return ts_array
end



def create_entries(result_transactions, all_trades, input, output)
    
  new_records = all_trades.dup
  file = File.new(output, "w")
    
  file.write("Instrument,Date,Time,Record Type,Price,Volume,Undisclosed Volume,Value,Qualifiers,Trans ID,Bid ID,Ask ID,Bid/Ask,Entry Time,Old Price,Old Volume,Buyer Broker ID,Seller Broker ID" + "\n")   
  i=0
    
  result_transactions.each do |n|
    if n == 'A'
      file.write("#{new_records[i][:instrument]},#{new_records[i][:date]},#{new_records[i][:time]},ENTER,#{new_records[i][:price]},100,#{new_records[i][:undisclosed_volume]},#{100*(new_records[i][:price].to_f)},#{new_records[i][:qualifiers]},,#{2*i},#{2*i + 1},A,#{new_records[i][:entry_time]},#{new_records[i][:old_price]},#{new_records[i][:old_volume]},#{new_records[i][:buyer_broker_id]},#{new_records[i][:seller_broker_id]}\n")          
    elsif n == 'B'
      file.write("#{new_records[i][:instrument]},#{new_records[i][:date]},#{new_records[i][:time]},ENTER,#{new_records[i][:price]},100,#{new_records[i][:undisclosed_volume]},#{100*(new_records[i][:price].to_f)},#{new_records[i][:qualifiers]},,#{2*i},#{2*i + 1},B,#{new_records[i][:entry_time]},#{new_records[i][:old_price]},#{new_records[i][:old_volume]},#{new_records[i][:buyer_broker_id]},#{new_records[i][:seller_broker_id]}\n")
    end      
    i=i+1
  end
	
  file.close
  $logfile.write("File created - see '#{output}'\n\n")
end

def read_flags
  parser = OptionParser.new do|opts|
    opts.banner = "Usage: msm_v<version> [options]"
    opts.on('-w window', '--window-size=window', 'Window Size for Moving Averages') do |window|
      $params[:n] = window.to_i;
    end

    opts.on('-t th', '--threshold=th', 'Threshold for moving averages') do |th|
      $params[:th] = th.to_f;
    end

    opts.on('-i inp', '--input=inp', 'Input file name (file must contain CSV data, and cannot be "newEntries.csv")') do |inp|
      $params[:i] = inp.to_s
    end

    opts.on('-o ou', '--output=ou', 'Output file name (file will contain CSV data)') do |ou|
      $params[:o] = ou.to_s
    end

    opts.on('-h', '--help', 'Displays Help') do
      puts opts
      exit
    end
  end

  parser.parse!

  $logfile.write("PARAMETERS ARE: n = #{$params[:n]}, TH = #{$params[:th]}, input = #{$params[:i]}, output = #{$params[:o]}\n\n")
end

def total_time
  end_time = Time.now
  $logfile.write("Ending time at " + end_time.inspect + "\n")
  $logfile.write("Total time was #{end_time - $start_time} seconds.");
end

    

############---MAIN---############

$start_time = Time.now
$params = { :n => 3,
            :th => 0.00005,
            :i => "bhp5Feb13.csv",
            :o => "newEntries.csv"}
$logfile.write("MSM MODULE - CREATED BY THE Gs\n")
$logfile.write("Starting time at " + $start_time.inspect + "\n")

#STEP 0
#$params[:n] = 3
#$params[:th] = 0.00005
#$params[:i] = "bhp5Feb13.csv"
#$params[:o] = "newEntries.csv"

#READING FLAGS
read_flags

n = $params[:n].to_i
thres = $params[:th].to_f
input = $params[:i].to_s
output = $params[:o].to_s

#STEP 1
all_trades = read_original(input)
  
#STEP 2
returns_array = find_returns(all_trades)

#STEP 3
sma_array = find_SMAs(n, returns_array)

#STEP 4
result_transactions = create_transactions(thres, sma_array)

#STEP 5
create_entries(result_transactions, all_trades, input, output)

#TOTAL TIME
total_time