package com.njust.service;

import com.njust.domain.Menu;
import com.njust.domain.SimpleUser;
import com.njust.dto.MenuDto;

import java.util.List;


public interface MenuService {

    /**
     * 查询菜单信息
     * 如查用户是超级管理员，那么查询所以菜单和权限
     * 如果用户是普通用户，那么根据用户ID关联角色和权限
     * @param isAdmin 是否是超级管理员
     * @param  simpleUser  如果isAdmin=true  simpleUser可以为空
     */
    public List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser);

    /**
     * 查询菜单数据
     * @param menuDto
     * @return
     */
    List<Menu> listAllMenus(MenuDto menuDto);

    /**
     * 添加菜单
     * @param menuDto
     * @return
     */
    int addMenu(MenuDto menuDto);

    /**
     * 修改菜单
     * @param menuDto
     * @return
     */
    int updateMenu(MenuDto menuDto);

    /**
     * 根据菜单ID查询一个
     * @param menuId
     * @return
     */
    Menu getOne(Long menuId);

    /**
     * 根据菜单ID判断有没有子节点
     * @param menuId
     * @return  true 说明有  false没有
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 根据菜单ID删除菜单
     * @param menuId
     * @return
     */
    int deleteMenuById(Long menuId);

    /**
     * 根据角色ID查询菜单权限ID数据
     * @param roleId
     * @return
     */
    List<Long> getMenusIdsByRoleId(Long roleId);
}
