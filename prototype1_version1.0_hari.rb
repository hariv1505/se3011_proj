require 'csv'
require 'test/unit'
extend Test::Unit::Assertions

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
  
  return all_trades
end
  
def find_returns(all_trades)
  prices = []
  #ensure all_trades is ordered by time
  all_trades.each do |trade|
    p = trade[:price]
    prices << p
    #ensure it is done in chronological order
  end
  assert_equal(all_trades.size, prices.size, "first failed")
  
  returns_array = []
  returns_array << nil
  prices.length.times do |i|
    if (i != 0)
      init_p = returns_array[i-1].to_f
      final_p = prices[i].to_f
      ret = (final_p - init_p)/init_p
      
      returns_array << ret.to_f
    end
  end
  assert_equal(all_trades.size, returns_array.size, "second failed")
  
  return returns_array
end
  
# def find_SMAs(n, returns) #(takes parameter for INTEGER we take average of - n)
#   #create array of SMAs - called SMA_array
#   #iterative from 'n'th element till last - OR 1st to (total-n)th [assume first now]
#   #for each element in returns:
#     #ignore first (n-1) elements
#     #after that, take the next n elements, and average them
#     #store them in the SMA_array in chronological order
#   #ensure size of this array is size of all_trades - (t-1)
#   
#   #return SMA_array
# end
#   
# def create_transactions(th, SMA_array)#(takes decimal/float parameter for threshold - th)
#   #absolute value th - lets call this threshold
#   #make array of TSvs (differences) - involves as per below:
#   #for each element of SMA_array
#     #if first, ignore (but track which 
#     #else
#       #take last value of new "TSvs" array
#       #find final-initial SMA (subtraction)
#       #record in new array TSvs
#   
#   #create new array of TS - what are we going to do: this should only store B or A for 'bid' or 'ask'. Call this TS_array
#   #for each TSvs, do:
#     #if elem < -'th' -> store an A
#     #elsif elem > 'th' -> store B
#     #else -> store null (to indicate do nothing)
#   
#   #return TS_array
# end
#   
# def create_entries(TS_array, all_trades) #create transactions in new CSV file using data
#   #create empty array of new record objects that have been generated
#   #these all have record type of ENTER
#   #for each elem of TS: (we need to create a record object and store it in the new array)
#     #Bid/Ask value (B or A) is taken from TS_array
#     #... (most is taken from respective transaction in all_trades, provided we keep index of each, which represents t value)
#   
#   #write out to new file "newEntries.csv"
#   #copy-paste line 1 of original CSV into new CSV somehow
#   #for each object:
#     #write out first characteristic
#     #for each other characteristic (not inclusive of first) (dunno if this can be done iteratively...)
#       # write out a comma
#       #	write out it out
#       #NB dont end with a comma
#     #write out newline
#   
#   #file is created in directory, do not return anything
# end

############---MAIN---############

#STEP 0
params = read_params('parameters/v1.0.param')
n = params[0][:n]
th = params[0][:th]
   
#STEP 1
all_trades = read_original('bhp5Feb13.csv')
  
#STEP 2
returns_array = find_returns(all_trades)
# 
# #STEP 3
# SMA_array = find_SMAs(n, returns_array)
# 
# #STEP 4
# result_transactions = create_transactions(th, SMA_array)
# 
# #STEP 5
# create_entries(TS_array, all_trades)
