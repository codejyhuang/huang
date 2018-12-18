package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.rpc.app.dao.model.system.Menu;
import com.hrym.rpc.app.dao.model.system.Role;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.app.util.TreeNode;
import com.hrym.app.service.MenuService;
import com.hrym.app.dao.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by xiaohan on 2017/10/10.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;



    //查询所有菜单
    @Override
    public Result findAllMenu(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        String name =null;
        String url =null;

        //分页核心代码
        PageHelper.startPage(page, rows);
        List<Menu> menus = menuMapper.findAllMenu();
//        String parentName = menuMapper.findNameById(parentId);

        PageInfo pageInfo = new PageInfo(menus);
        List<Map<String, Object>> list = new ArrayList<>();

        if (0<menus.size()){
            for (Menu menu : menus){
                Map<String,Object> map = Maps.newHashMap();
                map.put("menuId",menu.getMenuId());
                map.put("menuName",menu.getMenuName());

                if(menu.getPage() == "NULL"){
                    url = " ";
                }else{
                    url = menu.getPage();
                }

                map.put("page",url);



                if(menu.getParentId()=="-1"){
                    name= " ";
                }else{
                    name = menuMapper.findNameById(menu.getParentId());
                }

                map.put("parentId",name);

                list.add(map);
            }
        }
        long toal=pageInfo.getTotal();
        return new Result(code,message,toal,list);

    }


    //树形菜单的展示
    @Override
    public List<TreeNode> findByPid(Integer parentId) {
        return menuMapper.findByPid(parentId);
    }

    @Override
    public List<TreeNode> findMenuByPid(Integer parentId,Role role) {
        return menuMapper.findMenuByPid(parentId,role.getRoleId());
    }

    @Override
    public List<TreeNode> findById(Integer id) {
        return menuMapper.findById(id);
    }

    /**
     * 根据用户ID查找对应的菜单
     * @param
     * @return
     */
    @Override
    public List<Menu> findMenuPageByUserId(Integer userId) {

         List <Menu> list = menuMapper.findMenuPageByUserId(userId);

         for (Menu m : list){
            List<Menu> subMenu = menuMapper.findSubMenuPageByParentId(m.getMenuId());
            m.setMenus(subMenu);
         }
         return list;
    }


}
