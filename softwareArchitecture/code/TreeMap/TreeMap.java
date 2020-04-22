package tree;

public class TreeMap {
	TreeMapNode topNode = null;

	public void add(Comparable key, Object value) {
		if(topNode == null) 
			topNode = new TreeMapNode(key, value);
		else
			topNode.add(key, value);
	}

	public Object get(Comparable key) {
		return topNode == null ? null : topNode.find(key);
		 //search 메소드를 실현하기 위해 topNode가 null이면 null로 리턴하고 아니면 topNode.find(key)를 해줘라->top노드에서 sub노드로 찾아가기 위함
	}
	
	public void visitAll() {
		topNode.visit();//topNode의 TreeMapeNode에서 생성한 visit 메소드 호출
	}
	public void search(Comparable key) {
	    if(get(key) == null)//TreeMapNode의 get메소드에서 받은 key값이 null이면 
	          System.out.println("not found!");//not found 출력
	    else
	    	  System.out.println(key.toString());//아니면 key 값 출력
	   }
}

class TreeMapNode{
	TreeMapNode topNode = null;

	private final static int LESS = 0;
	private final static int GREATER = 1;
	private Comparable itsKey;
	private Object itsValue;
	private TreeMapNode nodes[] = new TreeMapNode[2];
	
	public TreeMapNode(Comparable key, Object value) {
		itsKey = key;
		itsValue = value;
	}
	
	public Object find(Comparable key) {
		if(key.compareTo(itsKey) == 0) return itsValue;
		return findSubNodeForKey(selectSubNode(key),key);
	}

	private int selectSubNode(Comparable key) {
		return(key.compareTo(itsKey) < 0) ? LESS : GREATER;
	}

	private Object findSubNodeForKey(int node, Comparable key){
		return nodes[node] == null ? null : nodes[node].find(key);
	}
	
	public void add(Comparable key, Object value) {
		if (key.compareTo(itsKey) == 0)
			 itsValue = value;
		else
			 addSubNode(selectSubNode(key), key, value);
	}

	private void addSubNode(int node, Comparable key, Object value) {
		 if (nodes[node] == null)
		 nodes[node] = new TreeMapNode(key, value);
		else
		 nodes[node].add(key, value);
	}
	public void visit() {
		int i;
		for(i=0;i<2;i++) {
			if(nodes[i] != null)	nodes[i].visit();//node[i]의 값이 null이 아니면 node[i]로 재귀 호출
		}
		System.out.println(itsKey.toString());//트리 안의 내용 출력
	}
}
