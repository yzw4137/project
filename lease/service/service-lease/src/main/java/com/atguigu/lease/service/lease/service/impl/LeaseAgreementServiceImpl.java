package com.atguigu.lease.service.lease.service.impl;

import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.model.enums.ItemType;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.graph.GraphVo;
import com.atguigu.lease.model.vo.lease.AgreementDetailVo;
import com.atguigu.lease.model.vo.lease.AgreementItemVo;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.atguigu.lease.model.vo.lease.AgreementVo;
import com.atguigu.lease.service.lease.mapper.*;
import com.atguigu.lease.service.lease.service.LeaseAgreementService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;
    @Autowired
    private LeaseTermMapper leaseTermMapper;

    @Override
    public IPage<AgreementVo> pageAgreement(Page<LeaseAgreement> page, AgreementQueryVo queryVo) {
        LambdaQueryWrapper<LeaseAgreement> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(queryVo.getApartmentId() != null, LeaseAgreement::getApartmentId, queryVo.getApartmentId());
        wrapper.like(queryVo.getName() != null && !queryVo.getName().isEmpty(), LeaseAgreement::getName, queryVo.getName());
        wrapper.like(queryVo.getPhone() != null && !queryVo.getPhone().isEmpty(), LeaseAgreement::getPhone, queryVo.getPhone());
        wrapper.orderByDesc(LeaseAgreement::getId);
        Page<LeaseAgreement> raw = this.page(page, wrapper);
        Page<AgreementVo> result = new Page<>(raw.getCurrent(), raw.getSize(), raw.getTotal());
        result.setRecords(enrichList(raw.getRecords()));
        return result;
    }

    @Override
    public AgreementVo getAgreementVoById(Long id) {
        LeaseAgreement la = getById(id);
        if (la == null) {
            return null;
        }
        AgreementVo vo = new AgreementVo();
        BeanUtils.copyProperties(la, vo);
        enrich(vo);
        return vo;
    }

    // ==================== 管理端富化（公寓/房间/支付方式/租期嵌套对象） ====================

    /** 富化单条租约 */
    private void enrich(AgreementVo vo) {
        if (vo.getApartmentId() != null) {
            vo.setApartmentInfo(apartmentInfoMapper.selectById(vo.getApartmentId()));
        }
        if (vo.getRoomId() != null) {
            vo.setRoomInfo(roomInfoMapper.selectById(vo.getRoomId()));
        }
        if (vo.getPaymentTypeId() != null) {
            vo.setPaymentType(paymentTypeMapper.selectById(vo.getPaymentTypeId()));
        }
        if (vo.getLeaseTermId() != null) {
            vo.setLeaseTerm(leaseTermMapper.selectById(vo.getLeaseTermId()));
        }
    }

    /** 批量富化租约列表（减少 N+1 查询） */
    private List<AgreementVo> enrichList(List<LeaseAgreement> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<Long> aptIds = new HashSet<>();
        Set<Long> roomIds = new HashSet<>();
        Set<Long> payIds = new HashSet<>();
        Set<Long> termIds = new HashSet<>();
        for (LeaseAgreement la : list) {
            if (la.getApartmentId() != null) aptIds.add(la.getApartmentId());
            if (la.getRoomId() != null) roomIds.add(la.getRoomId());
            if (la.getPaymentTypeId() != null) payIds.add(la.getPaymentTypeId());
            if (la.getLeaseTermId() != null) termIds.add(la.getLeaseTermId());
        }
        Map<Long, ApartmentInfo> aptMap = aptIds.isEmpty() ? Collections.emptyMap() : toMap(apartmentInfoMapper.selectBatchIds(aptIds));
        Map<Long, RoomInfo> roomMap = roomIds.isEmpty() ? Collections.emptyMap() : toMap(roomInfoMapper.selectBatchIds(roomIds));
        Map<Long, PaymentType> payMap = payIds.isEmpty() ? Collections.emptyMap() : toMap(paymentTypeMapper.selectBatchIds(payIds));
        Map<Long, LeaseTerm> termMap = termIds.isEmpty() ? Collections.emptyMap() : toMap(leaseTermMapper.selectBatchIds(termIds));

        List<AgreementVo> ret = new ArrayList<>(list.size());
        for (LeaseAgreement la : list) {
            AgreementVo vo = new AgreementVo();
            BeanUtils.copyProperties(la, vo);
            vo.setApartmentInfo(aptMap.get(la.getApartmentId()));
            vo.setRoomInfo(roomMap.get(la.getRoomId()));
            vo.setPaymentType(payMap.get(la.getPaymentTypeId()));
            vo.setLeaseTerm(termMap.get(la.getLeaseTermId()));
            ret.add(vo);
        }
        return ret;
    }

    private static <T extends BaseEntity> Map<Long, T> toMap(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<Long, T> map = new HashMap<>(list.size());
        for (T t : list) {
            map.put(t.getId(), t);
        }
        return map;
    }

    @Override
    public void updateStatusById(Long id, LeaseStatus status) {
        LambdaUpdateWrapper<LeaseAgreement> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(LeaseAgreement::getId, id);
        wrapper.set(LeaseAgreement::getStatus, status);
        this.update(wrapper);
    }

    @Override
    public List<LeaseAgreement> listByPhone(String phone) {
        LambdaQueryWrapper<LeaseAgreement> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LeaseAgreement::getPhone, phone);
        return this.list(wrapper);
    }

    @Override
    public void expireLeaseAgreement() {
        LambdaUpdateWrapper<LeaseAgreement> wrapper = new LambdaUpdateWrapper<>();
        wrapper.le(LeaseAgreement::getLeaseEndDate, new java.util.Date());
        wrapper.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING);
        wrapper.set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED);
        this.update(wrapper);
    }

    // ==================== App 端富化接口 ====================

    @Override
    public List<AgreementItemVo> listItemByPhone(String phone) {
        List<LeaseAgreement> list = listByPhone(phone);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<AgreementItemVo> ret = new ArrayList<>();
        for (LeaseAgreement la : list) {
            AgreementItemVo item = new AgreementItemVo();
            item.setId(la.getId());
            item.setLeaseStatus(la.getStatus());
            item.setLeaseStartDate(la.getLeaseStartDate());
            item.setLeaseEndDate(la.getLeaseEndDate());
            item.setSourceType(la.getSourceType());
            item.setRent(la.getRent());

            RoomInfo room = roomInfoMapper.selectById(la.getRoomId());
            if (room != null) {
                item.setRoomNumber(room.getRoomNumber());
                item.setRoomGraphVoList(listGraph(ItemType.ROOM, room.getId()));
                ApartmentInfo apartment = apartmentInfoMapper.selectById(room.getApartmentId());
                if (apartment != null) {
                    item.setApartmentName(apartment.getName());
                }
            }
            ret.add(item);
        }
        return ret;
    }

    @Override
    public AgreementDetailVo getDetailById(Long id) {
        LeaseAgreement la = getById(id);
        if (la == null) {
            return null;
        }
        AgreementDetailVo vo = new AgreementDetailVo();
        BeanUtils.copyProperties(la, vo);

        ApartmentInfo apartment = apartmentInfoMapper.selectById(la.getApartmentId());
        if (apartment != null) {
            vo.setApartmentName(apartment.getName());
            vo.setApartmentGraphVoList(listGraph(ItemType.APARTMENT, apartment.getId()));
        }

        RoomInfo room = roomInfoMapper.selectById(la.getRoomId());
        if (room != null) {
            vo.setRoomNumber(room.getRoomNumber());
            vo.setRoomGraphVoList(listGraph(ItemType.ROOM, room.getId()));
        }

        PaymentType paymentType = paymentTypeMapper.selectById(la.getPaymentTypeId());
        if (paymentType != null) {
            vo.setPaymentTypeName(paymentType.getName());
        }

        LeaseTerm leaseTerm = leaseTermMapper.selectById(la.getLeaseTermId());
        if (leaseTerm != null) {
            vo.setLeaseTermMonthCount(leaseTerm.getMonthCount());
            vo.setLeaseTermUnit(leaseTerm.getUnit());
        }
        return vo;
    }

    /** 查询某公寓/房间的图片列表 */
    private List<GraphVo> listGraph(ItemType itemType, Long itemId) {
        List<GraphInfo> list = graphInfoMapper.selectList(
                new LambdaQueryWrapper<GraphInfo>()
                        .eq(GraphInfo::getItemType, itemType)
                        .eq(GraphInfo::getItemId, itemId));
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream()
                .map(g -> new GraphVo(g.getName(), g.getUrl()))
                .collect(Collectors.toList());
    }
}
