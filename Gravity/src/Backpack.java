class Backpack {
    private int[] items;//An array that will hold the elements in the stack
    private int size;//Max number of elements that can be stored in the stack
    private int top;//Represents the index of the top elements in the stack

    //Constructor for the 'Backpack' class
    public Backpack(int size) {
        this.size = size;
        this.items = new int[size];////Initializes the items field to a new integer array of the specified size
        this.top = -1;//Setting the top as -1, indicates that the stack is empty
    }

    public boolean isFull() {//this method returns true if the stack is full(top index == size -1)
        return top == size - 1;
    }

    public boolean isEmpty() {//this method returns true if the stack is empty(top index == -1)
        return top == -1;
    }
    //push method adds an item to the top of the stack 
    public void push(int item) {
        if (!isFull()) { // First check if the stack is not full using isFull method
        	top ++;//Increment the top index
            items[top] = item;//Assign the value of the item to the items[top]
        }
    }
    //pop method removes and returns the item at the top of the stack
    public int pop() {
        if (!isEmpty()) { //First check if the stack is not empty using isEmpty method
            return items[top--];//Return the value of items[top], then decrement the top index
        }
        return -1;
    }
    //peek method returns the item at the top of the stack without removing it
    public int peek() {
        if (!isEmpty()) { //First check if the stack is not empty using isEmpty method
            return items[top];//Return the value of items[top]
        }
        return -1;
    }
    
    //checks two identical numbers
    public boolean hasMatchingTopTwoItems() {
        if (top >= 1 && items[top] == items[top - 1]) {//top>=1 means , there are at least two elements in the stack
            return true;
        }
        return false;
    }

    public void removeTopTwoItems() {
        if (hasMatchingTopTwoItems()) {
            items[top - 0] = 0;
            items[top - 1] = 0;
            top -=2;
        }
    }
    /*
	public void display() {
		
		 for (int item : items) {
		        System.out.print(item + " ");
		    }
		    System.out.println();
	}
	
	
	//add items to backpack
    public int addItem(int item) {
        int removedItem = -1;
        if (isFull()) {
            removedItem = pop();
        }
        push(item);
        return removedItem;
    }
     */
    
    
	public int[] getItems() {
		
		return items;
	}
}
