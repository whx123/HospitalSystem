package com.whx.action.hospitalInit;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.whx.paging.PageBean;
import com.whx.service.HospitalService;
public class HospitalInitAction implements Action{
	
	private HospitalService hospitalService;
	private String curPath;
	private String searchPro;
	
	private int page=1;
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private PageBean pageBean;
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public HospitalService getHospitalService() {
		return hospitalService;
	}

	public void setHospitalService(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	public String getCurPath() {
		return curPath;
	}
	public void setCurPath(String curPath) {
		this.curPath = curPath;
	}


	public String getSearchPro() {
		return searchPro;
	}

	public void setSearchPro(String searchPro) {
		this.searchPro = searchPro;
	}

	@Override
	public String execute() throws Exception {
		this.setCurPath("hospitalInitAction.action");
		this.pageBean=hospitalService.queryForPage(3,page);	
		return SUCCESS;
	}
	//���ݡ����Բ�ҽԺ������ҽԺ��˽��ҽԺ��
	public String searchHospitalByProperty()throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String property= new String(request.getParameter("pro").getBytes("iso-8859-1"),"utf-8");
		this.setCurPath(property);
		this.setSearchPro(property);
		property="%"+property+"%";
		this.curPath="searchHospitalByProperty.action";
		this.pageBean=hospitalService.queryForPageH2(3, page, property);
		return SUCCESS;	
	}
	public String searchHospitalByArea()throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String area= new String(request.getParameter("pro").getBytes("iso-8859-1"),"utf-8");
		this.setSearchPro(area);
		area="%"+area+"%";
		this.curPath="searchHospitalByArea.action";
		this.pageBean=hospitalService.queryForPageH3(3, page, area);
		System.out.print("pageBean"+pageBean);
		if(pageBean.getAllRow()==0){
			pageBean.setCurrentPage(1);
			pageBean.setTotalPage(1);
		}
		return SUCCESS;	
	}
	public String searchHospitalByRank()throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rank= new String(request.getParameter("pro").getBytes("iso-8859-1"),"utf-8");
		this.setSearchPro(rank);
		rank="%"+rank+"%";
		System.out.println("---------"+rank);
		this.curPath="searchHospitalByRank.action";
		this.pageBean=hospitalService.queryForPageH4(3, page, rank);
		if(pageBean.getAllRow()==0){
			pageBean.setCurrentPage(1);
			pageBean.setTotalPage(1);
		}
		return SUCCESS;	
	}
	public String searchHospital()throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String hospitalName= new String(request.getParameter("pro").getBytes("iso-8859-1"),"utf-8");
		/*this.setCurPath(hospitalName);*/
		this.setSearchPro(hospitalName);
		hospitalName="%"+hospitalName+"%";
		this.curPath="searchHospital.action";
		this.pageBean=hospitalService.queryForPageH(3, page, hospitalName);
		return SUCCESS;	
	}
	

}
