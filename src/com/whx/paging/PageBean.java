package com.whx.paging;

import java.util.List;
public class PageBean {
	private List list;
	private int allRow;
	private int totalPage;
	private int currentPage;
	private int pageSize;// ÿҳ��¼��
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	private boolean isFirstPage;// �Ƿ�Ϊ��һҳ
	private boolean isLastPage;// �Ƿ�Ϊ���һҳ
	private boolean hasPreviousPage;// �Ƿ���ǰһҳ
	private boolean hasNextPage;// �Ƿ�����һҳ
	public List getList(){
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = hasNextPage();
	}
	private boolean isHasPreviousPage() {
		// TODO Auto-generated method stub
		return currentPage != 1; // ֻҪ��ǰҳ���ǵ�1ҳ
	}
	private boolean isFirstPage() {
		// TODO Auto-generated method stub
		return currentPage == 1; // ���ǵ�ǰҳ�ǵ�1ҳ
	}
	private boolean isLastPage() {
		// TODO Auto-generated method stub
		return currentPage == totalPage; // �����ǰҳ�����һҳ
	}
	private boolean hasNextPage() {
		// TODO Auto-generated method stub
		return currentPage != totalPage; // ֻҪ��ǰҳ�������1ҳ
	}
	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
				/ pageSize + 1;
		return totalPage;
	}
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}
}

	

