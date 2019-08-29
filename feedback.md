# Project 2 (uMessage) Feedback #
## CSE 332 Winter 2018 ##

**Team:** Ivy Wang (fw29) and Alexis Macaskill (alexism9) <br />
**Graded By:** Ollin Boer Bohan (ollin@cs.washington.edu)
<br>

## Unit Tests ##

**AVLTree**  `(6/6)`
> ✓ Passed *initialize* <br>
> ✓ Passed *insert* <br>
> ✓ Passed *getters* <br>
> ✓ Passed *sorted_duplicate_input* <br>
> ✓ Passed *unsorted_duplicate_input* <br>
> ✓ Passed *structure* <br>

**MoveToFrontList**  `(6/6)`
> ✓ Passed *initialize* <br>
> ✓ Passed *insert* <br>
> ✓ Passed *getters* <br>
> ✓ Passed *sorted_duplicate_input* <br>
> ✓ Passed *unsorted_duplicate_input* <br>
> ✓ Passed *structure* <br>

**HashTable**  `(6/6)`
> ✓ Passed *initialize* <br>
> ✓ Passed *insert* <br>
> ✓ Passed *getters* <br>
> ✓ Passed *sorted_duplicate_input* <br>
> ✓ Passed *unsorted_duplicate_input* <br>
> ✓ Passed *comparator* <br>
> ✓ Passed *negative_hash_codes* <br>

**HeapSort**  `(5/5)`
> ✓ Passed *integer_inorder* <br>
> ✓ Passed *integer_reverseorder* <br>
> ✓ Passed *integer_interleaved* <br>
> ✓ Passed *integer_random* <br>
> ✓ Passed *string* <br>
> ✓ Passed *dataCount_string* <br>

**QuickSort**  `(5/5)`
> ✓ Passed *integer_inorder* <br>
> ✓ Passed *integer_reverseorder* <br>
> ✓ Passed *integer_interleaved* <br>
> ✓ Passed *integer_random* <br>
> ✓ Passed *string* <br>
> ✓ Passed *dataCount_string* <br>

**TopKSort**  `(6/6)`
> ✓ Passed *integer_random_100* <br>
> ✓ Passed *string_20* <br>
> ✓ Passed *dataCount_ngram_counts_inorder* <br>
> ✓ Passed *dataCount_ngram_counts_reverseorder* <br>
> ✓ Passed *dataCount_ngram_counts_interleaved* <br>
> ✓ Passed *dataCount_ngram_counts_random* <br>

**CircularArrayHashCode**  `(1/2)`
> ✓ Passed *generate_hash_codes* <br>
> `✘ Failed` *hash_overlap* <br>
> ✓ Passed *high_variance* <br>
> `✘ Failed` *with_null_chars* <br>

**CircularArrayComparator**  `(2/2)`
> ✓ Passed *vary_length* <br>
> ✓ Passed *vary_order* <br>

**NGramToNextChoicesMap** ∞ Timeout `(1/2)`
> ✓ Passed *poem* <br>
> `∞ Timeout` *large* <br>
> `` *15000ms* <br>

**UMessage** ∞ Timeout `(5/6)`
> ✓ Passed *simple_HashTable_AVL* <br>
> ✓ Passed *simple_HashTable_HashTable* <br>
> ✓ Passed *simple_HashTable_AVL_topk* <br>
> ✓ Passed *simple_HashTable_HashTable_topk* <br>
> ✓ Passed *poem_HashTable_AVL* <br>
> ✓ Passed *poem_HashTable_HashTable* <br>
> ✓ Passed *poem_HashTable_MTF* <br>
> ✓ Passed *poem_HashTable_AVL_topk* <br>
> ✓ Passed *poem_HashTable_HashTable_topk* <br>
> ✓ Passed *poem_HashTable_MTF_topk* <br>
> `∞ Timeout` *large_HashTable_AVL_topk* <br>
> `∞ Timeout` *large_HashTable_HashTable_topk* <br>

## Miscellaneous ##













## Write-Up ##

**Project Enjoyment**
`(3/3)`
It's good to hear you enjoyed this project!  And your feedback is appreciated. The refactoring portion is definitely underspecified-I think the intention is that you get the experience of a refactor in an existing codebase (common in non-homework programming projects, but not covered in any other CS courses). But I think we could definitely accomplish that while still giving more guidance about how to solve the problems that arise during the refactor.

**BST vs. AVLTree**
`(4/4)`
Nice job! One note-it looks like you wrote O(n) for your BST prediction when (from context) you meant O(n^2), which is sort of confusing. But otherwise your experiments look sensible and your conclusions make sense based on the data you observed.

**ChainingHashTable**
`(3/3)`
Good work testing on both sorted / unsorted data here! Your data presentation makes sense and your analysis seems reasonable based on the data collected.

**Hash Functions**
`(3/3)`
Looks good! The explanation of your results makes sense. Although the labels on your second graph are confusing (from reading your analysis, it seems like the orange line is hash function 2, not function 1).

**General Purpose Dictionary**
`(3/3)`
-0: Looks good! Though it would have been a bit nicer to do an asymptotic analysis by varying the size of the input (proportion of the file inserted, for example).

**uMessage**
`(3/3)`
Congratulations on getting uMessage working! And interesting analysis of the suggestion quality for each suggestion mechanism-I think modern iOS uses LSTM language models to predict the next word (meaning it's much more powerful than the ngram models here), but the version of autocorrect in the first iPhone was about as good as what we've implemented.

**Above & Beyond**
`(EX: 0)`
