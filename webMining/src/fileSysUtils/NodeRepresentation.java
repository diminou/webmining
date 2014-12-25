/**
 * 
 */
package fileSysUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author divanov
 *
 */
public class NodeRepresentation {

	private NodeRepresentation parent;
	private Set<NodeRepresentation> children;
	private String pathValue;
	
	public NodeRepresentation(){
		super();
		this.parent = null;
		this.children = new HashSet<NodeRepresentation>();
	}
	
	public NodeRepresentation(NodeRepresentation parent){
		super();
		this.parent = parent;
		this.children = new HashSet<NodeRepresentation>();
	}
	
	public NodeRepresentation(NodeRepresentation parent, String pathValue){
		this.parent = parent ;
		this.children = new HashSet<NodeRepresentation>();
		this.pathValue = pathValue ;
	}

	public boolean hasParent() {
		if (parent == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isLeaf(){
		if(children.isEmpty()){
			return true;
		}else{
			return false;
		}
		
	}

	public NodeRepresentation getParent() {
		return parent;
	}

	public void setParent(NodeRepresentation parent) {
		this.parent = parent;
	}

	public Set<NodeRepresentation> getChildren() {
		return children;
	}

	public void setChildren(Set<NodeRepresentation> children) {
		this.children = children;
	}
	
	public void addChild(NodeRepresentation child){
		this.children.add(child);
	}
	
	public void removeChild(NodeRepresentation child){
		this.children.remove(child);
	}
	

	public String getPathValue() {
		return pathValue;
	}

	public void setPathValue(String pathValue) {
		this.pathValue = pathValue;
	}
	
	public String getFullPath(){
		if(this.hasParent()){
			return this.parent.getFullPath() + File.pathSeparator + this.pathValue; 
		} else{
			return this.pathValue;
		}
	}
	
	
}
