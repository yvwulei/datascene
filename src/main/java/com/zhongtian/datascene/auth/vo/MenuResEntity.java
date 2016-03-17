package com.zhongtian.datascene.auth.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 菜单资源
 * @author 
 *
 */
@Table(name="t_menu_res")
@Entity
public class MenuResEntity implements Comparable<MenuResEntity> {
	public static final String RES_TYPE="menu";
	private int id;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单标识
	 */
	private String sn;
	/**
	 * 菜单的位置 AuthFinalVal 定义的常量
	 * top left footer model_nev model_oper
	 */
	private int menuPos;
	/**
	 * 菜单的url
	 */
	private String href;
	/**
	 * 菜单使用的图表
	 */
	private String icon;
	/**
	 * 菜单序号
	 */
	private int orderNum;
	/**
	 * 菜单父节点的标识
	 */
	private String psn;
	/**
	 * 显示标识
	 */
	private int display;
	private MenuResEntity parent;
	
	public MenuResEntity(){
	}
	
	public MenuResEntity(int id, String name, String sn, int menuPos,
			String href, String icon, int orderNum, String psn, int display) {
		super();
		this.id = id;
		this.name = name;
		this.sn = sn;
		this.menuPos = menuPos;
		this.href = href;
		this.icon = icon;
		this.orderNum = orderNum;
		this.psn = psn;
		this.display = display;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 菜单的名称，中文名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 菜单的sn，不能重复，将来要通过这个sn自动生成页面的超链接，然后为超链接增加一个属性auth_sn，
	 * 值就是sn
	 * @return
	 */
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * 菜单所在的位置
	 * @return
	 */
	@Column(name="menu_pos")
	public int getMenuPos() {
		return menuPos;
	}
	public void setMenuPos(int menuPos) {
		this.menuPos = menuPos;
	}
	/**
	 * 菜单的超链接
	 * @return
	 */
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	/**
	 * 菜单的图标
	 * @return
	 */
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 是否显示菜单，1表示显示，-1表示不显示
	 * @return
	 */
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	/**
	 * 菜单的排序号
	 * @return
	 */
	@Column(name="order_num")
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 菜单的父类sn，方便初始化的时候做操作
	 * @return
	 */
	public String getPsn() {
		return psn;
	}
	public void setPsn(String psn) {
		this.psn = psn;
	}
	/**
	 * 菜单的父类菜单，在授权的时候比较方便
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name="pid")
	public MenuResEntity getParent() {
		return parent;
	}
	public void setParent(MenuResEntity parent) {
		this.parent = parent;
	}

	@Override
	public int compareTo(MenuResEntity o) {
		return (this.orderNum>o.orderNum)?1:((this.orderNum==o.orderNum)?0:-1);
	}
	
}
