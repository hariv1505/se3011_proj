#need to create record object

def main
  
  #NB: we track the t's so that we know where to get original data from for Step 5
  #need to create log file
  #need to figure out format of parameters file
  
  #1
  #read in CSV file
  #create array of record objects that will store all "TRADES"
  
  #create a map of indexes from line 1
  
  #iterate through each row
    #if line[index of "Record type"] == TRADE
      #create "record" object for this line
      #add to array of "TRADES"
    #else
      #go next
  
  #2.
  #create array of "prices" (empty now)
  #ensure "TRADES" array is ordered by time
  #for each record in "TRADES"
    #take price
    #add to array of prices
    #ensure it is done in chronological order
  #ensure size of this array is sizes of "TRADES"
  
  #create array of "returns" (starts off empty)
  #for each value in "prices"
    #if its the first, skip
    #else
      #take last value of new "returns" array
      #do final - initial price, divided by initial
      #store decimal in returns array in chronological order
  #ensure size of this array is one less than "TRADES" or "prices"
  
  #3. (takes parameter for number we take average of - n)
  #create array of SMAs
  #iterative from 'n'th element till last - OR 1st to (total-n)th [assume first now]
  #for each element:
    #ignore first (n-1) elements
    #after that, take the next n elements, and average them
    #store them in the SMAs array in chronological order
  #ensure size of this array is size of "TRADES" - (t-1)
  
  #4. (takes parameter for threshold - TH)
  #absolute value TH
  #make array of TSvs (differences) - involves as per below:
  #for each element of SMAs
    #if first, ignore (but track which 
    #else
      #take last value of new "TSvs" array
      #find final-initial SMA
      #record in new array TSvs
  #create new array of TS - what are we going to do: this should only store B or A for 'bid' or 'ask'
  #for each TSvs, do:
    #if elem < -TH -> store an A
    #elsif elem > TH -> store B
    #else -> store null
  
  #5. create transactions using data
  #create empty array of new record objects that have been generated
  #these all have record type of ENTER
  #for each elem of TS: (we need to create a record object and store it in the new array)
    #B/A is stored from TS array
    #... (most is taken from old arrays, provided we keep index of each, which represents t value)
  
  #write out to new file "newEntries.csv"
  #copy-paste line 1 of original CSV into new CSV somehow
  #for each object:
    #write out first characteristic
    #for each other characteristic (not inclusive of first) (dunno if this can be done iteratively...)
      # write out a comma
      #	write out it out
      #NB dont end with a comma
    #write out newline
  
end