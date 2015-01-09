package fileSysUtils;

public class MainForestier {

	public static void main(String[] args) {
		TreeRepresentation root = new TreeRepresentation("naturisme", null);
		System.out.println(root.toString());
		
		root.insert("boloss", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("xenophobe", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("excellent", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("ablation", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("acne", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("aperitif", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("apx", null);
		root = root.root();
		System.out.println(root.toString());
		

	}

}
