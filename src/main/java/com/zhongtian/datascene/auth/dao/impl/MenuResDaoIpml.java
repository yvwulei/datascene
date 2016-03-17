package com.zhongtian.datascene.auth.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhongtian.datascene.auth.dao.IMenuResDao;
import com.zhongtian.datascene.auth.dto.LeftMenuDto;
import com.zhongtian.datascene.auth.dto.TreeDto;
import com.zhongtian.datascene.auth.vo.AuthFinalVal;
import com.zhongtian.datascene.auth.vo.MenuResEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;
import com.zhongtian.datascene.basic.excepiton.SysException;
import com.zhongtian.datascene.basic.util.BasicSysKitUtil;

@Repository("menuResDao")
public class MenuResDaoIpml extends BaseDaoHibernateImpl<MenuResEntity> implements IMenuResDao {

	@Override
	public void add(MenuResEntity mr, String psn) {
		
		MenuResEntity parent = null;
		if(!BasicSysKitUtil.isEmpty(psn)) {
			parent = (MenuResEntity)this.loadBySn(psn);
			if(parent==null) throw new SysException("菜单的父类不存在");
			mr.setParent(parent);
			mr.setPsn(psn);
		}
		MenuResEntity self = (MenuResEntity)this.loadBySn(mr.getSn());
		if(self==null) {
			//不存在
			self = mr;
		} else {
			//存在就进行修改
			self.setDisplay(mr.getDisplay());
			self.setHref(mr.getHref());
			self.setIcon(mr.getIcon());
			self.setMenuPos(mr.getMenuPos());
			self.setName(mr.getName());
			self.setOrderNum(mr.getOrderNum());
			self.setSn(mr.getSn());
			self.setParent(mr.getParent());
			self.setPsn(mr.getPsn());
		}
		super.saveOrUpdate(self);
	}

	@Override
	public List<MenuResEntity> listModelMenuByType(String psn, int pos) {
		String hql = getSelectHql()+" from MenuResEntity m where m.psn=? and m.menuPos=? and m.display=1 order by m.orderNum";
		return super.findList(hql, psn,pos);
	}

	@Override
	public List<MenuResEntity> listTopMenu() {
		String hql = getSelectHql()+" from MenuResEntity m where m.menuPos=? and m.display=1 order by m.orderNum";
		return super.findList(hql,AuthFinalVal.MENU_TOP_NAV);
	}

	@Override
	public List<LeftMenuDto> listLeftNav() {
		String hql = "select m from MenuResEntity m where m.menuPos=?";
		List<MenuResEntity> mrs = super.findList(hql, AuthFinalVal.MENU_LEFT_NAV);
		List<LeftMenuDto> lmds = new ArrayList<LeftMenuDto>();
		LeftMenuDto lmd = null;
		//添加菜单的父节点
		for(MenuResEntity mr:mrs) {
			if(mr.getParent()!=null&&mr.getParent().getMenuPos()==AuthFinalVal.MENU_ROOT) {
				//菜单是父节点菜单
				lmd = new LeftMenuDto();
				lmd.setParent(mr);
				lmd.setChilds(new ArrayList<MenuResEntity>());
				lmds.add(lmd);
			}
		}
		
		//添加子节点菜单
		for(MenuResEntity mr:mrs) {
			MenuResEntity mp = mr.getParent();
			if(mp!=null&&mp.getMenuPos()==AuthFinalVal.MENU_LEFT_NAV) {
				LeftMenuDto tmp = new LeftMenuDto(); tmp.setParent(mp);
				if(lmds.contains(tmp)) {
					lmds.get(lmds.indexOf(tmp)).getChilds().add(mr);
				}
			}
		}
		
		//排序父节点菜单
		Collections.sort(lmds);
		
		//排序子菜单
		for(LeftMenuDto md:lmds) {
			Collections.sort(md.getChilds());
		}
		
		return lmds;
	}

	@Override
	public List<TreeDto> tree() {
		String sql = "select id,name,pid from t_menu_res where display=1 order by order_num";
		List<TreeDto> tds = super.findListBySql(sql, TreeDto.class, false);
		return tds;
	}

	@Override
	public List<MenuResEntity> listByParent(Integer pid) {
		String hql = null;
		if(pid==null||pid==0)
			hql = getSelectHql()+" from MenuResEntity m where m.parent is null and m.display=1 order by m.orderNum";
		else
			hql = getSelectHql()+" from MenuResEntity m where m.display=1 and m.parent.id="+pid+" order by m.orderNum";
		return super.findList(hql);
	}
	
	private String getSelectHql() {
		return "select new MenuResEntity(m.id,m.name,m.sn,m.menuPos,m.href,m.icon,m.orderNum,m.psn,m.display,m.orderNum,m.pid) ";
	}

	@Override
	public MenuResEntity loadBySn(String sn) {
		String hql = "from MenuResEntity mr where mr.sn=?";
		return (MenuResEntity)this.findObject(hql, sn);
	}

	@Override
	public List<MenuResEntity> listByRoleId(Integer rid) {
		String hql  = " select mr from MenuResEntity mr ,AclEntity a where a.rtype='menu' and a.stype='role' and a.rid=mr.id and a.sid = ? ";
		return super.findList(hql,rid);
	}

	@Override
	public List<MenuResEntity> listByUserId(Integer uid) {
		String hql  = "select mr from MenuResEntity mr ,AclEntity a ,UserRoleEntity ur where a.rtype='menu' and a.stype='role' and a.rid=mr.id and ur.rid=a.rid and a.uid = ? ";
		return super.findList(hql,uid);
	}

}
