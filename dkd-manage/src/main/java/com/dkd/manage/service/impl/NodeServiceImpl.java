package com.dkd.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dkd.common.utils.DateUtils;
import com.dkd.common.utils.bean.BeanUtils;
import com.dkd.manage.domain.VendingMachine;
import com.dkd.manage.domain.vo.NodeVO;
import com.dkd.manage.mapper.VendingMachineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.NodeMapper;
import com.dkd.manage.domain.Node;
import com.dkd.manage.service.INodeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 点位管理Service业务层处理
 *
 * @author Smasun
 * @date 2026-05-06
 */
@Service
public class NodeServiceImpl implements INodeService {
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private VendingMachineMapper vendingMachineMapper;

    /**
     * 查询点位管理
     *
     * @param id 点位管理主键
     * @return 点位管理
     */
    @Override
    public Node selectNodeById(Long id) {
        return nodeMapper.selectNodeById(id);
    }

    /**
     * 查询点位管理列表
     *
     * @param node 点位管理
     * @return 点位管理
     */
    @Override
    public List<Node> selectNodeList(Node node) {
        return nodeMapper.selectNodeList(node);
    }

    /**
     * 新增点位管理
     *
     * @param node 点位管理
     * @return 结果
     */
    @Override
    public int insertNode(Node node) {
        node.setCreateTime(DateUtils.getNowDate());
        return nodeMapper.insertNode(node);
    }

    /**
     * 修改点位管理
     *
     * @param node 点位管理
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNode(Node node) {
        //根据点位Id查询设备列表
        List<VendingMachine> vmList = vendingMachineMapper.selectVendingMachineByNodeId(node.getId());
        for (VendingMachine vm : vmList) {
            //同步修改设备列表中的冗余字段
            vm.setAddr(node.getAddress());
            vm.setBusinessType(node.getBusinessType());
            vm.setRegionId(node.getRegionId());
            vm.setPartnerId(node.getPartnerId());
            vm.setUpdateTime(DateUtils.getNowDate());
            vendingMachineMapper.updateVendingMachine(vm);
        }
        //更新点位信息
        node.setUpdateTime(DateUtils.getNowDate());
        return nodeMapper.updateNode(node);
    }

    /**
     * 批量删除点位管理
     *
     * @param ids 需要删除的点位管理主键
     * @return 结果
     */
    @Override
    public int deleteNodeByIds(Long[] ids) {
        return nodeMapper.deleteNodeByIds(ids);
    }

    /**
     * 删除点位管理信息
     *
     * @param id 点位管理主键
     * @return 结果
     */
    @Override
    public int deleteNodeById(Long id) {
        return nodeMapper.deleteNodeById(id);
    }

    /**
     * 查询点位管理列表
     *
     * @param node 点位管理
     * @return 点位管理集合
     */
    public List<NodeVO> selectNodeListVO(Node node) {
        return nodeMapper.selectNodeListVO(node);
    }


}
