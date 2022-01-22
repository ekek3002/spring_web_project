package org.zerock.domain;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Data
@RequiredArgsConstructor
@Log4j
public class ReplyPageDTO {
	private final int replyCnt;
	private final List<ReplyVO> list;
}
