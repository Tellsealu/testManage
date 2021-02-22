package com.njust.service;

import com.njust.domain.Notice;
import com.njust.dto.NoticeDto;
import com.njust.vo.DataGridView;


public interface NoticeService {
    /**
     * 分页查询
     *
     * @param noticeDto
     * @return
     */
    DataGridView listNoticePage(NoticeDto noticeDto);

    /**
     * 根据ID查询
     *
     * @param noticeId
     * @return
     */
    Notice getOne(Long noticeId);

    /**
     * 添加
     *
     * @param noticeDto
     * @return
     */
    int addNotice(NoticeDto noticeDto);

    /**
     * 修改
     *
     * @param noticeDto
     * @return
     */
    int updateNotice(NoticeDto noticeDto);

    /**
     * 根据ID删除
     *
     * @param noticeIds
     * @return
     */
    int deleteNoticeByIds(Long[] noticeIds);

}
