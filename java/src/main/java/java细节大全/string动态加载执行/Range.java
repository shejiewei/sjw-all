package java细节大全.string动态加载执行;

import java.io.Serializable;

public class Range implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	
	private boolean include_upper = false;
	
	private boolean include_lower = true;
	
	private Double to;
	
	private Double from;

	public Double getTo() {
		return to;
	}

	public void setTo(Double to) {
		this.to = to;
	}

	public Double getFrom() {
		return from;
	}

	public void setFrom(Double from) {
		this.from = from;
	}

	public boolean isInclude_upper() {
		return include_upper;
	}

	public void setInclude_upper(boolean include_upper) {
		this.include_upper = include_upper;
	}

	public boolean isInclude_lower() {
		return include_lower;
	}

	public void setInclude_lower(boolean include_lower) {
		this.include_lower = include_lower;
	}

	public String getKey() {
		if(key == null || "".equals(key.trim())) {
			return this.toString();
		}
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		if(from != null && to != null) {
			if(include_lower && include_upper) {
				str.append("[").append(from).append(",").append(to).append("]");
			} else if(include_lower) {
				str.append("[").append(from).append(",").append(to).append(")");
			} else if(include_upper) {
				str.append("(").append(from).append(",").append(to).append("]");
			} else {
				str.append("(").append(from).append(",").append(to).append(")");
			}
		} else if(from != null) {
			if(include_lower) {
				str.append("[").append(from).append(",").append("*").append(")");
			} else {
				str.append("(").append(from).append(",").append("*").append(")");
			}
		} else if(to != null) {
			if(include_upper) {
				str.append("(").append("*").append(",").append(to).append("]");
			} else {
				str.append("(").append("*").append(",").append(to).append(")");
			}
		}
		return str.toString();
	}
}
