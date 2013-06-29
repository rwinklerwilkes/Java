class Queue
{
	private Node firstNode;
	private Node lastNode;
	public int size=0;
	public Queue(int m)
	{
		Node n = new Node(m);
		firstNode=n;
		lastNode=n;
	}
	public Queue()
	{
		firstNode = null;
		lastNode = null;
	}
	public void insertAfter(Node newNode, Node node)
	{
		newNode.prev=node;
		newNode.next=node.next;
		if(node.next!=null)
		{
			node.next.prev=newNode;
		}
		else
		{
			lastNode=newNode;
		}
		node.next=newNode;
	}
	public void insertBefore(Node newNode, Node node)
	{
		newNode.prev=node.prev;
		newNode.next=node;
		if(node.prev!=null)
		{
			node.prev.next=newNode;
		}
		else
		{
			firstNode=newNode;
		}
		node.prev=newNode;
	}
	public void insertBeginning(Node node)
	{
		if(firstNode==null)
		{
			firstNode=node;
			lastNode=node;
			node.prev=null;
			node.next=null;
		}
		else
		{
			insertBefore(node, firstNode);
		}
	}
	public void insertEnd(Node node)
	{
		if(lastNode==null)
		{
			insertBeginning(node);
		}
		else
		{
			insertAfter(node,lastNode);
		}
	}
	public int removeEnd()
	{
		int val = lastNode.value;
		Node m = lastNode;
		if(m!=firstNode)
		{
			m.prev.next=null;
		}
		lastNode=m.prev;
		m.prev=null;
		m.next=null;
		return val;
	}
	public void push(int n)
	{
		Node node = new Node(n);
		insertBeginning(node);
		size++;
	}
	public int pop()
	{
		int value = removeEnd();
		size--;
		return value;
	}
}
class Node
{
	public int value;
	public Node prev;
	public Node next;
	public Node(int n)
	{
		value = n;
		prev=null;
		next=null;
	}
}