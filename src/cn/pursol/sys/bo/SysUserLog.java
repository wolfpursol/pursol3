package cn.pursol.sys.bo;

/**
*<p>Title:日志</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.util.bo.BaseObject;

public class SysUserLog extends BaseObject implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Integer logid;
	private String userip;
	private String createdate;
	private String descript;
	private String userid;

	public SysUserLog(){
	}

 	public Integer getLogid() {
		return this.logid;
	}

	public void setLogid(Integer logid) {
		this.logid=logid;
	}

	public String getUserip() {
		return this.userip;
	}

	public void setUserip(String userip) {
		this.userip=userip;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate=createdate;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript=descript;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid=userid;
	}

public String getId() {
return String.valueOf(this.logid);
}
	public String toString() {
		return new ToStringBuilder(this)
		.append("logid",getLogid()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysUserLog))
		return false;
		SysUserLog castOther = (SysUserLog)other;
		return new EqualsBuilder().append(logid, castOther.logid).isEquals();
	}

public int hashCode() {
		return new HashCodeBuilder().append(logid).toHashCode();
	}

}