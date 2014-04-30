require 'csv'
require 'test/unit'
require 'Time'
extend Test::Unit::Assertions

$logfile = File.new("log.txt", "w")

def read_csv(parameter_file)
  s = IO.read(parameter_file)
  csv = CSV.new(s, {:headers => true, :header_converters => :symbol})
  return csv.to_a.map {|row| row.to_hash }
end

def read_params(parameter_file)
  read_csv(parameter_file)

end

def read_original(sirca_file)
  entries = read_csv(sirca_file) 
  all_trades = []
  counter = 0
  entries.each do |entry|
    if (entry[:record_type] == "TRADE")
      all_trades << entry
    end
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
  endme


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
  
  #puts returns.size
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
  
def create_transactions(th, sma_array)
  threshold = th.abs

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
#   TEST IF IT IS BUYING AND SELLING APPROPRIATELY
#   [MUST ALSO CONSIDER ROUNDING ERROR - JUST REALISED]
#   ts_array.size.times do |b|
#     if (ts_array[b] != nil)
#       print ts_array[b]
#     else
#       print "nil"
#     end
#     print " "
#     if (tsv_array[b] != nil)
#       puts tsv_array[b]
#     else
#       puts "nil"
#     end
#   end
  
  return ts_array
end



def create_entries(result_transactions, all_trades)
    
    new_records = all_trades.dup    
    file_array = IO.readlines("bhp5Feb13.csv") #can change input file to a parameterfile.
    file = File.new("newEntries.csv", "w")
    file.write("#{file_array[0]}")   
    #counter to concurrently iterate through new_records with corresponding result_transaction value 
    i=0
    
  result_transactions.each do |n|
      if n == 'A'
	#If BID/ASK is A, the characteristics will be written to file with record_type changed to ENTER and bidask value as ASK
	file.write("#{new_records[i][:instrument]},#{new_records[i][:date]},#{new_records[i][:time]},ENTER,#{new_records[i][:price]},#{new_records[i][:volume]},#{new_records[i][:undisclosed_volume]},#{new_records[i][:value]},#{new_records[i][:qualifiers]},#{new_records[i][:trans_id]},#{new_records[i][:bid_id]},#{new_records[i][:ask_id]},ASK,#{new_records[i][:entry_time]},#{new_records[i][:old_price]},#{new_records[i][:old_volume]},#{new_records[i][:buyer_broker_id]},#{new_records[i][:seller_broker_id]}\n")          
      elsif n == 'B'
	#If BID/ASK is A, the characteristics will be written to file with record_type changed to ENTER and bidask value as BID
	file.write("#{new_records[i][:instrument]},#{new_records[i][:date]},#{new_records[i][:time]},ENTER,#{new_records[i][:price]},#{new_records[i][:volume]},#{new_records[i][:undisclosed_volume]},#{new_records[i][:value]},#{new_records[i][:qualifiers]},#{new_records[i][:trans_id]},#{new_records[i][:bid_id]},#{new_records[i][:ask_id]},BID,#{new_records[i][:entry_time]},#{new_records[i][:old_price]},#{new_records[i][:old_volume]},#{new_records[i][:buyer_broker_id]},#{new_records[i][:seller_broker_id]}\n")
      end      
      i=i+1
    end
	
    file.close
    $logfile.write("File created - see 'newEntries.csv'\n\n")
end
    

############---MAIN---############

start_time = Time.now
#puts "Starting time at " + start_time.inspect

#STEP 0
params = read_params('parameters.param')
n = params[0][:n].to_i
th = params[0][:th].to_f
$logfile.write("PARAMETERS ARE: n = #{n}, TH = #{th}, input = #{params[0][:i]}, output = input = #{params[0][:o]}\n\n")

#READING FLAGS
ARGV.size.times do |i|
  if (ARGV[i].to_s == "-i" || ARGV[i].to_s == "--input") {
    params[0][:i] = ARGV[i+1];
  } elsif (ARGV[i].to_s == "-o" || ARGV[i].to_s == "--output") {
    params[0][:o] = ARGV[i+1];
  } elsif (ARGV[i].to_s == "-n") {
    params[0][:n] = ARGV[i+1];
  } elsif (ARGV[i].to_s == "-th" || ARGV[i].to_s == "--threshold") {
    params[0][:th] = ARGV[i+1];
  }
end
  
#STEP 1
all_trades = read_original(params[0][:i])
  
#STEP 2
returns_array = find_returns(all_trades)

#STEP 3
sma_array = find_SMAs(n, returns_array)

#STEP 4
result_transactions = create_transactions(th, sma_array)

#STEP 5
create_entries(result_transactions, all_trades)

#TOTAL TIME
end_time = Time.now
#puts "Ending time at " + end_time.inspect
$logfile.write("Total time was #{end_time - start_time} seconds.");
