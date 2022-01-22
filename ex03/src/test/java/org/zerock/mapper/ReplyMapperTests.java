package org.zerock.mapper;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.List;
import java.util.stream.IntStream;

import javax.sound.midi.MidiDevice.Info;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.controller.SampleControllerTests;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = { 105L, 104L, 103L, 102L, 101L};
	
	@Autowired
	private ReplyMapper mapper;
	
	@Test
	public void testMapper(){
		log.info(mapper);
	}

	@Test
	public void testCreate(){
		IntStream.rangeClosed(1, 10).forEach(i->{
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트"+i);
			vo.setReplyer("replyer"+i);
			
			mapper.insert(vo);
			
		});
	}
	
	@Test
	public void restRead(){
		Long rargetRno = 107L;
		ReplyVO vo = mapper.read(rargetRno);
		
		log.info("restRead : "+vo);
	}
	
	@Test
	public void testDelete(){
		Long targetRno = 2L;
		
		mapper.delete(targetRno);
	}
	
	@Test
	public void restUpdate(){
		Long targetRno = 166L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("update reply....");
		int cunt = mapper.update(vo);
		log.info("UPDATE COUNT : "+cunt);
		
	}
	
	@Test
	public void testList(){
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[4]);
		
		replies.forEach(i -> log.info("replies list ....... : "+i));
	}
	
	@Test
	public void testList2(){
		Criteria cri = new Criteria(1, 10);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 101L);
		
		replies.forEach(reply -> log.info("reply ="+reply));
	}
}
