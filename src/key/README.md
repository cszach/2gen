<h1>NOTES</h1>

<code>excludeKeyFileName</code> is an argument for list (array) of files that the user chooses to exclude those keys in the file(s), <br/>
or in other words, avoid creating a key that is similar to any of the keys in the files <br/>
<br/>
If <code>excludeKeyFileName</code> is <code>null</code>, there's no exception. <br/>
If it is not, the program will avoid creating similar keys by scanning through the files whose names are in <code>excludeKeyFileName</code>, <br/>
and if there's one matching, the program will try to create another random key. <br/>
This is done by a while loop, marked as <code>mainLoop</code>.