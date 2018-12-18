package com.hrym.app.controller;



import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.dao.model.system.BackUser;
import com.hrym.rpc.app.dao.model.system.Menu;
import com.hrym.rpc.app.dao.model.system.Role;
import com.hrym.rpc.app.util.TreeNode;
import com.hrym.app.service.BackUserService;
import com.hrym.app.service.MenuService;
import com.hrym.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hrym.rpc.app.util.Result;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohan on 2017/9/29.
 */

@Controller
@RequestMapping(value = "/admin")
public class SystemController extends BaseController {

   @Autowired
   private MenuService menuService;
   @Autowired
   private RoleService roleService;
   @Autowired
   private BackUserService backUserService;

   //查询所有用户
   @RequestMapping("/user_list")
   @ResponseBody
   public Result findAllBackUser(BackUser backUser) {
       return backUserService.findAllBackUser(backUser.getPageNumber(),backUser.getPageSize());

   }

   //角色列表《修改角色时用》
    @RequestMapping("/findALLRole")
    @ResponseBody
    public Result findALLRole(){

       return backUserService.findALLRole();
    }

    @RequestMapping("/user_search")
    @ResponseBody
    public Result findByName(BackUser backUser)  throws Exception{
        //自负转码
        if (null != backUser.getUsername()){
            //转码
            String val = URLDecoder.decode(backUser.getUsername(), "UTF-8");
            backUser.setUsername(val);
        }
        return backUserService.findByName(backUser,backUser.getPageNumber(),backUser.getPageSize());

    }

    @RequestMapping("/user_change")
    @ResponseBody
    public Result changeStatus(BackUser backUser) {

        return backUserService.changeStatus(backUser);

    }

    /**
     * 后台用户注册
     * @param backUser
     * @return
     */
    @RequestMapping("/insertUser")
    @ResponseBody
        public Result insertUser(BackUser backUser){

       return backUserService.insertUser(backUser);
        }

    /**
     * 删除用户列表
     * @param id
     * @return
     */
    @RequestMapping("/deleteBackUserByUserId")
    @ResponseBody
    public Result deleteBackUserByUserId(Integer id){

        return backUserService.deleteBackUserByUserId(id);
    }

    /**
     * 修改用户角色
     * @param id
     * @return
     */
    @RequestMapping("/updateRoleId")
    @ResponseBody
    public Result updateRoleId(Integer id,Integer roleId){

       return backUserService.updateRoleId(id,roleId);
    }
    //  获取所有角色，生成checkbox列表
    @RequestMapping("/role_box")
    @ResponseBody
    public List<Role> findRoles() {
        return roleService.findRoles();

    }

    //查询角色列表
    @RequestMapping("/role_list")
    @ResponseBody
    public Result findAllRole(Role role) {
        return roleService.findAllRole(role.getPageNumber(),role.getPageSize());

    }

    //保存用户及菜单
    @RequestMapping("/role_save")
    @ResponseBody
    public Result saveRole(Role role,String roleMenuIds) {

            return roleService.saveRole(role,roleMenuIds);
    }

//
    @RequestMapping("/showMenuById")
    @ResponseBody
    public List<TreeNode> findMenuById(Role role) {

        List<TreeNode> treeNodes = roleService.findMenuById(role);
        List<TreeNode> list = new ArrayList<>();
        TreeNode treeNode = treeNodes.get(0);
        list.add(treeNode);
//        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(buildTree(treeNodes));
//        System.out.println(jsonArray.toString());

        return buildTree(list,role);
    }

    // 查询菜单列表
    @RequestMapping("/menu_list")
    @ResponseBody
    public Result findAllMenu(Menu menu) {
        return menuService.findAllMenu(menu.getPageNumber(),menu.getPageSize());

    }

    //查询生成的树菜单
    @RequestMapping("/node_list")
    @ResponseBody
    public List<TreeNode> getNodeList(@RequestParam(value="id",defaultValue = "0")Integer id){
       List<TreeNode>  treeNodes = menuService.findById(id);
//        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(buildTree(treeNodes));
//        System.out.println(jsonArray.toString());

        return buildTree(treeNodes);
    }

    public List <TreeNode> buildTree(List<TreeNode>  treeNodes){
        for(int i=0;i<treeNodes.size();i++){
            List<TreeNode> children = menuService.findByPid(treeNodes.get(i).getId()); //查询某节点的子节点（获取的是list）
            buildTree(children);
            treeNodes.get(i).setChildren(children);
        }
        return treeNodes;

    }

    public List <TreeNode> buildTree(List<TreeNode> treeNodes,Role role){
        for(int i=0;i<treeNodes.size();i++){
            List<TreeNode> children = menuService.findMenuByPid(treeNodes.get(i).getId(),role); //查询某节点的子节点（获取的是list）
            buildTree(children,role);
            treeNodes.get(i).setChildren(children);
        }
        return treeNodes;
    }

    //test测试
    @RequestMapping("/findMenuPageByUserId")
    @ResponseBody
    public List<Menu> findMenuPageByUserId(Integer userId){
        return menuService.findMenuPageByUserId(1001);
    }


    /**
     * 根据角色ID删除角色表和角色菜单关联表的相关内容
     * @param id
     * @return
     */
    @RequestMapping("/deleteRoleByRoleId")
    @ResponseBody
     public Result deleteRoleByRoleId(Integer id){

        return roleService.deleteRoleByRoleId(id);
    }



}
