import java.util.Iterator;

public class BuildBSTList {

	public static void main(String[] args)
	{
		// start with ArrayOrderedList of integers
		ArrayOrderedList<Integer> arrList = new ArrayOrderedList<Integer>();
		for (int i = 1; i <= 10; i++)
		{
			arrList.add(new Integer(i));
		}
		System.out.println(arrList);	// show what it looks like
		
		// create BinarySearchTreeList by adding items from ArrayOrderedList in order
		BinarySearchTreeList<Integer> bstList = new BinarySearchTreeList<Integer>();
		
		Iterator<Integer> iter = arrList.iterator();
		while (iter.hasNext())
			bstList.add(iter.next());
			
		// print tree in level order to show what it looks like
		iter = bstList.iteratorLevelOrder();
		while (iter.hasNext())
			System.out.print(iter.next() + " ");
		System.out.println();
		
		// now build a balanced tree
		bstList = new BinarySearchTreeList<Integer>();
		buildBalanced(arrList, bstList);
		
		// print list in level order to show what it looks like
		iter = bstList.iteratorLevelOrder();
		while (iter.hasNext())
			System.out.print(iter.next() + " ");
		System.out.println();
		
	}
	
	// build binary search tree list from array ordered list so that it is
	// as balanced as possible
	
	public static void buildBalanced(ArrayOrderedList<Integer> arrList,
			BinarySearchTreeList<Integer> bstList)
	{
		Integer [] tempArr = new Integer[arrList.size()];
		int index = 0;
		Iterator<Integer> iter = arrList.iterator();
		while (iter.hasNext())
		{
			tempArr[index] = iter.next();
			index ++;
		}
		
		buildBalancedRec(tempArr, 0, tempArr.length - 1, bstList);
	}
		
	// recursive method that adds items in the specified array to the specified
	// binary search tree list such that the tree stay as balanced as possible
	
	
	//changed the void to BinaryTreeNode to return. header in the lab originally was "void" but i could not get it to compile
	/*private static BinaryTreeNode buildBalancedRec(Integer [] tempArr, int start, int end, BinarySearchTreeList<Integer> bstList)
	{		
		// your code goes here
		 int mid = start + (end - start) / 2;
		 BinaryTreeNode node = new BinaryTreeNode(tempArr[mid]);
		if (start < end){
		      node.setLeft(buildBalancedRec( tempArr,start, (mid - 1), bstList));
		      node.setRight(buildBalancedRec( tempArr,(mid + 1), end, bstList));
		      return node;
		  }
		  else if(start > end) {
		      return null;
		    }
		return node;
	}*/
	
	//original code:
	private static void buildBalancedRec(Integer [] tempArr, int start, int end, BinarySearchTreeList<Integer> bstList)
	{		
		// your code goes here
		  if (start < end){
			 int mid = start + (end - start) / 2;
			 BinaryTreeNode node = new BinaryTreeNode(tempArr[mid]);
		      node.left = buildBalancedRec( tempArr,start, (mid - 1), bstList);
		      node.right = buildBalancedRec( tempArr,(mid + 1), end, bstList);
		      //return node;
		  }
		  else if(start > end) {
		    //  return null;
		    }
	}
	

}
