class InputQueue {
	
	private int rear, front;
	private Object[] elements;
	
	
	InputQueue(int capacity){
		elements = new Object[capacity];
		rear = -1;
		front = 0;		
	}
	
	
	void enqueue(char data) {
		if(isFull())
			System.out.println("Queue overflow");
		else {
			rear = (rear + 1) % elements.length;
			elements[rear] = data;
		}
	}
	
	char dequeue() {
		if(isEmpty()) {
			System.out.println("Queue is empty");
			return 'e';
		}
		else {
			char retData = elements[front].toString().charAt(0);
			elements[front]= null;
			front = ( front + 1 ) % elements.length;
			return retData;
		}
	}
	
	char peek() {
		if(isEmpty()) {
			System.out.println("Queue is empty");
			return 'e';
		}else {
			return elements[front].toString().charAt(0);
		}
	}
	
	
	boolean isEmpty() {
		if(elements[front] == null)
			return true;
		else
			return false;
	}
	
	
	boolean isFull() {
		return(front == (rear + 1) % elements.length && elements[front] != null && elements[rear] != null);
		
	}
	
	int size() {
		if(elements[front] == null) {
			return 0;
			
		}else {
			if(rear >= front)
				return rear - front + 1;
			else
				return elements.length - (front - rear) + 1;
		}	
	}

}
