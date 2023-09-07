package ch.supsi.webapp.server;
enum Type{IMAGE, DOCUMENT}
public class Content
{
	int length = 0;
	byte[] content;
	boolean isNotFound=false;
	Type type=Type.DOCUMENT;
	public Content(byte[] content){
		this.content = content;
		this.length = content.length;

	}
}