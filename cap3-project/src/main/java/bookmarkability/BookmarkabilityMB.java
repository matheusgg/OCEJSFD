package bookmarkability;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BookmarkabilityMB implements Serializable {

	private static final long serialVersionUID = 2035802810499860950L;
	
	private Integer itemID;

	/**
	 * @return the itemID
	 */
	public Integer getItemID() {
		return itemID;
	}

	/**
	 * @param itemID
	 *            the itemID to set
	 */
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

}
