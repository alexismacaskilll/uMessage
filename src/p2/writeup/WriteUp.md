# Project 2 (uMessage) Write-Up #
--------

## Project Enjoyment ##
- What was your favorite part of the project? Why?
  <pre> Our favorite part of the project was the AVL tree. We both found the AVL tree the most challenging, but it was fun to actually apply what we were learning in class to the project. We practice AVL insertions and finds in class several times; we even looked at some code of how the rotations would look. Once we implemented all of the logic for all the rotations, we were still getting a time out error. We used the visualizer and debugged that way. This was the first time we really used the visualizer to debug and we both enjoyed getting to know how to use it. </pre>

- What was your least favorite part of the project? Why?
  <pre>
  Our least favorite part of the project was changing the dependence of Java's HashMap with our Chaining HashTable. This was frusterating because we didn't know how to fix the mismatch between the type of iterator returned from ChainingHashTable and the one that you need in HashTrieNode. The only instruction provided by the spec was that we have to add a bit of code to make the HashTrieNode/HashTrieMap to work around this type mismatch, but we found that clue very vague. We then were told that we have to find some way to repackage the items that Chaining Hashtable Table's iterator returns. Even after we were told this, we struggled to write an iterator using the SimpleEntry java doc. It would have been nice if there was more instructions in the spec because this would have helped us.

</pre>

- How could the project be improved? Why?
  <pre> We think the project could be improved by making the spec more clear on how to change the n dependency using the SimpleEntry java doc. The project could also have been improved by providing more information on the Supplier javadoc used in the NGramToNextChoicesMap. The last thing we thought could improve the project would be to add more questions in the write up about UMessage's suggestions given different corpus's. Compared to how much time we ran running experiments on the different data structures, we hardly spent any time actually running UMessage, which is a little sad considering we spent 3 weeks working on it. </pre>

- Did you enjoy the project?  Why or why not?
  <pre> 
  	Overall we enjoyed the project. The data structures we were implementing actually aligned with what we were learning in class, unlike the first project. The first project's experiment also didn't seem to align with anything we were learning in class. We had no idea what buffer length or compression ratio was, so we didn't know how to check our results with what we thought they should be. This project's experiment was different. After making the graphs for all the different data sets, we could tell what data sets were clearly off, and which we believed were accurate.
  </pre>
    
-----

## Experiments ##
Throughout p1 and p2, you have written (or used) several distinct implementations of the Dictionary interface:
 - HashTrieMap 
 - MoveToFrontList
 - BinarySearchTree
 - AVLTree
 - ChainingHashTable
 
 In this Write-Up, you will compare various aspects of these data structures.  This will take a significant amount of
 time, and you should not leave it to the last minute.  For each experiment, we expect you to:
 - Explain how you constructed the inputs to make your conclusions
 - Explain why your data supports your conclusions
 - Explain your methodology (If we wanted to re-run your experiment, we would be able to.)
 - Tell us what inputs you used.  If generated in the code, tell us where and how. If from a file, please describe the files and include them in the experiments folder.
 - Include your data either directly in the write-up or in the experiments folder
 - You should include graphs of the outputs for at least a few of the questions.
   You can add a link to an image following the instructions found here:
https://courses.cs.washington.edu/courses/cse332/18wi/handouts/markdown.pdf
An example can also be found at the end of this file.
 - We recommend that you keep your "N" (as in "N-gram") constant throughout these experiments. (N = 2 and N = 3 are reasonable.) 
 - You should probably run multiple trials for each data point to help remove outliers.
 - You should not need to wait for hours and hours for your program to run in
 order to answer a question.  Do use large values for N, but not so large that 
 you are waiting overnight for your program to run (N=1,000,000 is likely larger 
 than you need.).


### BST vs. AVLTree ###
Construct inputs for BST and AVLTree to demonstrate that an AVL Tree is asymptotically better
than a Binary Search Tree. Comparing the worst case for each structure is fine here. 
To do this, we expect you to show trends.  You might consider fitting a curve to
your results. Explain your intuition on why your results are what they are.
<pre>
In order to examine the worst case for a BST and an AVL Tree, we our inputs that we constructed the tree's with were sorted integers from 0 to whatever we set our constant, NUM_ELEMENTS, equal to. Even though timing is difficult to get accurate results, we chose to use timing for our experiment because it takes into account issues like the cost of memory accesses. To improve the accuracy of our data, we decided to run 60 trials total forthe operation that we tested, constructing the tree, which is basically testing the insert operation. We only chose to only test insert because insert and find have roughly the same runtime behaviors. The prediction that we made before running any trials is the worst case construct runtime of the BST should be O(n) and the AVL tree should be O(nlogn), which means insert for BST should be O(n) and insert for AVL should be O(logn). This is because the insert times for both the BST and the AVL tree are based on the worst case height, but the worst case height for the BST can be O(n), where it would be a singly linked list, and the worst case height for the AVL tree is log(n) because of the AVL balance property. Out of the 60 trials, we decided to throw away the first 10 trials because Java optimizes repeated operations, so the first few times a piece of code runs it runs more slowly. So in our construct experiment for each tree, BST and AVL, we started the timer and inserted sorted integers 0 through NUM_ELEMENTS into the tree, and then stopped the timer. We only added the trial's time to the totalTime variable if the trial was greater than the number of warm up trials that we set, which in this case we set 10. We then divided by the total number of trials that we ran, which was equal to NUM_TESTS - NUM_WARMUP. First we will compare the worst case construct performance for the AVL Tree and the BST. From the graph below, which has the construct behavior for both BST and AVL tree, it looks as if, the AVL tree is asymptotically better. 
</pre>
![](<BSTVSAVLFinal1.png>)
<pre>
It looks like the BST tree's runtime is quadratic, but it isn't clear what the AVL tree's runtime is. The reason why BST presents a quadratic construct time is because the BST is unbalanced. An unbalanced BST exhibits, in the worst case, a singly linked list. Constructing this list will require that each insertion goes down the full length of the growing list to get to the leaf node to add a new node, thus creating a quadratic runtime. This graph shows that the AVL tree is asymptotically better than the BST. To know the exact performance of AVL tree, lets look at the construct performance of the AVL Tree on a separate graph. So below is the graph of just the AVL construct performance. 
</pre>
![](<justAVL.png>)

<pre>
Note, we have already proved that the AVL tree's construct performance is asymptotically better than the BST Tree. Also since construct is made up of n inserts, it is essentially proving that insert for AVL is asymptotically better than BST, which also means that it is better for find as well since insert and find have roughly the same runtime behaviors. Although this graph seem like the AVL tree has a linear construct time, we know that this is not true, it has a runtime of O(nlogn). If you actually look at the points on the graph, you can tell this is not linear. For example, take the three data points: (4000, 1.38), (8000, 3.14), and (16000, 7.56). As the number of elements is doubling from 4000 --> 8000 --> 16000, the construct time in milliseconds is increasing by more than just a multiple of two. For example, 1.38 * 2.275 = 3.14, and 3.14 * 2.408 = 7.56. So it is clear from this that the construct runtime is not just linear, but something close to O(nlogn). This is because the AVL tree has the balance property which guarantees that an AVL tree with n nodes has O(logn) height, which means the total time to construct an AVL tree with n nodes is O(nlogn). 
</pre>


### ChainingHashTable ###
Your ChainingHashTable should take as an argument to its constructor the type of "chains" it uses.  Determine
which type of chain is (on average, not worst case) best: an MTFList, a BST, or an AVL Tree.  Explain your intuition on why
the answer you got makes sense (or doesn't!). 
<pre>
For this experiment, we used our really good hash function that we implemented in part2. We tested three different types of chains for MTFList, a BST, and AVL Tree with two different types of data, sorted and unsorted. The time it took our table construct a hashtable with these different types of chains is shown below. 

</pre>
![](<sortedALl.png>)
![](<unsortedAll.png>)

<pre>
As you can see, the runtime for all three types of chains are extremely similar, so there is no clear winner. This is because our hash function is really good, so each element is hashed basically to a unique index. So inserting elements is not very expensive: it only takes O(1) to find the index from the hashcode, but then it needs to check the entire chain to see if that element already exists in the chain. However, since our hash function is so good, each chain only has around one element, so all chains will perform equally as well. In the graphs showing the sorted data, there are some big increases in the performance time between certain inputs for number of elements. This is most likely the place where the hashtable has to resize, meaning it has to rehash the entire table, which is very expensive. It makes sense that these jumps take place at the same time in the sorted number graph because each chain is inputing the same number of sorted numbers, so they will both run out of indexes at the same time. within the sorted data, because the table fills up the indexes time in the smooth  but this takes a while, but that means each chain is really short. I think that these jumps are more smoothed over in  the unsorted data because different indexes are filling up at different times, so one chain could fill all its indexes a little faster because the random numbers hashed to have a low number of numbers in every index, while another chain could fill its indexes slower because more of the random numbers hash to the same index at first, putting more numbers in each index. These two chains would rehash at different times because they fill every index at different times. 

So we are saying there is no clear winner; however, if we would have used our very bad hash function instead of our good hash function, we would have got the AVL tree or the BST tree as the winner. Comparing these hash functions is discussed further in the following experiment, but I will explain why very briefly. With our bad hash function, we would have a lot of numbers hashing to the same index. Since insert has to search all of the elements to make sure there are no duplicates, inserting in the hashtable would be dependent on how fast the table can traverse all the elements in a specific index. Because of this we know that AVL Tree would be the winner in the WORST case, but since we are looking at the average case, it could be either AVL tree or BST since they both have a O(logn) find and insert time.   
</pre>
 
### Hash Functions ###
Write a new hash function (it doesn't have to be any good, but remember to include the code in your repository).
Compare the runtime of your ChainingHashTable when the hash function is varied.  How big of a difference can the
hash function make (on average, not worst case)?  (You should keep all other inputs (e.g., the chain type) constant.)  Explain your intuition on
why your results are what they are.

<pre>We use circularArrayFIFOQueue of Integer with a capacity of 5 as the key to test the efficiency of different hash functions because by using this data structure we can change the hash function easily. In order to change the hash function of it, we make a CircularArrayFIFOQueueToTest class, which extends to the circularArrayFIFOQueue. We will use that class as our key. Our input data is generated randomly by the GenerateData.java. This program randomly generates 5 integer(0 - 99) a line, which represents all the works in a circularArray. We choose MoveToFront as our chain since when the number of elements in the chain increasing, the length of the chain increase linearly which means we can see the difference of time easily. The two hash function we test is the 1.sum of (peek(i).hashCode() * Math.pow(37, i)) 2.(0 < i < capacity) and peek(0).hashCode().</pre>

![](<ConstructTimeForHashFunction.png>)
![](<FindTimeForHashFunction.png>)

<pre>
When we do the test, we use HashCodeTiming.java to time. First, we decide how many elements we should insert into this hashTable, then fill the the number in NUM_ELEMENTS GenerateData. Second, we run the GenerateData to get our random data. The data is output in randomData.txt. Third we choose which hash function to test, 1 for the sum of (peek(i).hashCode() * Math.pow(37, i)), and i is from 0 to capacity - 1; 2 for peek(0).hashCode(). We fill that number in Hash_Function at HashCodeTiming. Fourth, We run the HashCodingTiming, to see construct time and find time. Then, we run the HashCodeTiming again with the hash function we haven't choose in step 3. In order to make the data identical, we do not need to run the Generate Data again in this step. Now, we get a set of data and we will repeat those steps to get the data on different numbers of elements.
</pre>

<pre>
We tested both the construct and find time. Here are our results.When we do the find test, we comment out the test of construct part, which may save us a lot of time. In the find test we find all the elements inside the chaining hash table, then divide by the size of the chaining hash table to get an average find time.For construct part, the time used to construct a chainghashtable both increase when the number of elements increasing. Because our chain is getting longer and we have more thing to insert. The hashcode function 1 performance a litter better than the hashcode function 2. However, there's not that much difference when the number of elements is small.When the number of elements become huge, there is a big difference. hash function 1 performed much better than the hash function two. We think that since the hash function two just based on the first elements in the circular array, a lot of elements are hashed to same bucket. Then the chain becomes very long, which took a lot of time to insert. However, for the hash function 1, elements tend to be hashed into difference buckets, which make the chain short and easy to insert. When the number of elements is small, we think since there are not many elements, the chain when using hash function two is also short.  Also, the hashcode function 1 is way more complicated than hashcode function 2, we spend a lot of time to compute the hashcode. So, there's not much difference when number of elements is small. When the number of elemetns become huge, the chain  become super long when using hash function 2, which make the construction very slow. So for the construction part, the difference of hashcode function is huge when the number of elements is large.For find, as we can see from the graph, the time is increasing in both hash functions when the number of elements increasing. For the hash function 1, it almost looks like a flat line and the it increase very slightly when n increasing. However, for the hash function 2, the time increases linearly when n increase. For small amount of data(n<=5000), the hash function 1 performed slightly better than the hash code 2, but it is not a big differnet. However, when n continunes grow, the difference between the find time become large. And we think the different will become larger and larger when n keep increasing. We think this is because the hashcode hash 2 hashes a lot of thing to the same bucket, which make the chain become longer. However, for the hashcode function one, the elements tends to be hash to different buckets which make the chain shorter and we find elements faster.When n is small, the chain when use those hash function is both short since there are not many elements. Thus, there's not a big difference when n is small. When n is large, the chain when use hash function 2 is long however the chain when using hash function 1 is short. The difference will become larger and larger when n increases.Also, when n = 100000, the time hashcode function 2 took is 30 times of hashcode function 1 took. So there it will make a big differnece when choosing different hash function.</pre>



### General Purpose Dictionary ###

Compare BST, AVLTree, ChainingHashTable, and HashTrieMap on alice.txt.  Is
there a clear winner?  Why or why not?  Is the winner surprising to you?

<pre>We use the word in side the alice.txt as the key of our dictionary and the number of the appearance as the value to construct our dictionary.We use GeneralPurpose.java to test. We test both construction and find. When doing the test, we just need to comment out the data structure we do not use, then just run the program. For chaining hashtable we test different implements of chains. We will get a construction time and an average find time. For the find, we find every word in the Alice then divide by the size of the dictionary.</pre>

<pre>
As we can see from the graph, the winner is ChainingHash table since the chaining Hash table has a shortest construct time and shortest find time. It did not surprised me, because if we have a perfect hash function, the find method for every word would be O(1). Although we do not have a perfect hash function, the find time is still near a constant time. However for BST or AVL, for average case it is O(logN) for find. AVL is better since the it is a balanced tree and the height of the tree is less than BST.  For HashTrieMap, it saves more space but spend more time to construct. What surprise me is that in those tests, MoveToFrontList as the chain of chaining hash table has a best performance. I think it might because the hash function is really good and the chain for each bucket is very short. So the simple MTFL performs better as the chain in chaining hash table.
</pre>
![](<AliceConstruct.png>)
![](<AliceFind.png>)


### uMessage ###
Use uMessage to test out your implementations.  Using N=3, uMessage should take less than a minute to load using your best algorithms and data structures on a reasonable machine.
 -  How are the suggestions uMessage gives with the default corpus? (here we mean spoken.corpus or irc.corpus, not eggs.txt)
    <pre> I think the suggestions with the uMessage with the default corpus are very reasonable; however, it is definitely less reasonable than the suggestions from my iOS phone. For example, I use texting to coordinate and ask my friends about their plans. So on my phone, if I type "want to go get some," my next word suggestions is "stuff", "dinner", and "lunch". Using the default irc.corpus, after the words "want to go get some," my next word suggestions are "uh", "of", "help", and "money", which are words I don't use as much. The same goes for spoken.corpus,after the words "want to go get some," my next word suggestions are "more", "sort", "ace", and "all", which are words I don't use as much.  It seems as if my iOS word suggestion suggests words that would form shorter sentences, while the irc.corpus makes very long sentences. This is because my iOS keyboard learns based on my personal dictionary of words that I use. In addition, I frequently use the word suggestion, which causes me to use the same few words very frequently, which makes my iOS word suggestion dictionary's vocabulary very limited. Another difference between the default corpus word suggestions and the iOS word suggestion, is that not all the suggestions from the default corpus makes sense to me, but all of the iOS suggestions make sense to me. I think this is the result you get from iOS actually building its dictionary from words and phrases that I have wrote before. This ensures no weird phrases that I would never use come up, but irc.corpus and spoken.corpus do not do the same. The irc.corpus is also different than the word suggestion on iOS because it does not pick up on the informal/slang language that I use when texting other people. My iOS phone does not even suggest going anymore; it only suggests gonna. The irc.corpus does not know the slang that I use, so it cannot do this.  </pre>
 </pre>

 - Now, switch uMessage to use a corpus of YOUR OWN text. To do this, you will need a corpus. 
   You can use anything you like (Facebook, google talk, e-mails, etc.)  We provide
   instructions and a script to format Facebook data correctly as we expect it will be the most common
   choice.  If you are having problems getting data, please come to office hours and ask for help.
   Alternatively, you can concatenate a bunch of English papers you've written together to get a corpus
   of your writing.  PLEASE DO NOT INCLUDE "me.txt" IN YOUR REPOSITORY.  WE DO NOT WANT YOUR PRIVATE CONVERSATIONS.
     * Follow these instructions to get your Facebook data: https://www.facebook.com/help/212802592074644
     * Run the ParseFBMessages program in the p2.wordsuggestor package.
     * Use the output file "me.txt" as the corpus for uMessage.
 
 - How are the suggestions uMessage gives with the new corpus?
   <pre> In order to generate the me.txt file, I compiled about 10 of my previous English papers. The suggestions uMessage gives is still reasonable, but they are not the suggestions I would want for a messaging service. First off, it is very obvious these suggestions come from English papers. For example, if I type "the," the suggestions are "past," "present," "audience," and "harsh." With the exception of "harsh," all of these words are essential in having a strong argument in an English paper. In addition, the word suggestions are a lot more formal than my iOS word suggester. They are even more formal than the irc.corpus or the spoken.corpus. The only place that the word suggestions do not make a lot of sense is when I type questions. This is probably because the me.txt corpus is generated from my argument-based English papers and I do not ask questions in these papers. In fact, there is hardly any occurrences of the word "you" in this corpus because English teachers don't allow you to use "you" in your writing. For example, the words that follow, "do you" are "are," "should," "cannot," and "from." These nonsense suggestions come from the limited dictionary that is caused by the diction and word choice that English teachers require. This new corpus is also different than the word suggestion on iOS because it does not pick up on the informal/slang language that I use when texting other people. This is because slang language is not acceptable in my English papers.  </pre>

-----

A sample image:

![](husky.jpg)

To show you how it is done.

## Above and Beyond ##
-   Did you do any Above and Beyond?  Describe exactly what you implemented.
 <pre>TODO</pre>
