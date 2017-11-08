package br.com.cuidebem;

import java.io.File;

public class FileMail {
	
	private String cid;
	private File file;

	
	public FileMail() {
		super();
	}
	public FileMail(String cid, File file) {
		super();
		this.cid = cid;
		this.file = file;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	

}
