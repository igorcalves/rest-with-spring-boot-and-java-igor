package br.com.igor.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.igor.data.vo.v1.BookVO;
import br.com.igor.mapper.DozerMapper;
import br.com.igor.model.Book;
import br.com.igor.unittest.mapper.mocks.MockBook;

public class DozerConverterBookTest {
    
    MockBook inputObject;
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeEach
    public void setUp() {  
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() throws Exception{
    	Date dmock = sdf.parse("11/11/1111");
        BookVO output = DozerMapper.parseObject(inputObject.mockEntity(), BookVO.class);
        
        assertEquals(0, output.getKey());
        assertEquals("Authro Test0", output.getAuthor());
        assertEquals("Text Test0", output.getTitle());
        assertEquals(dmock, output.getLaunchDate());
        assertEquals(0.00, output.getPrice());
        
    }
    
    @Test
    public void parseEntityListToVOListTest()throws Exception {
    	Date dmock = sdf.parse("11/11/1111");
        List<BookVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), BookVO.class);
        BookVO outputZero = outputList.get(0);
        
        assertEquals(0, outputZero.getKey());
        assertEquals("Authro Test0", outputZero.getAuthor());
        assertEquals("Text Test0", outputZero.getTitle());
        assertEquals(dmock, outputZero.getLaunchDate());
        assertEquals(0.00, outputZero.getPrice());
        
        BookVO outputSeven = outputList.get(7);
        
        assertEquals(7, outputSeven.getKey());
        assertEquals("Authro Test7", outputSeven.getAuthor());
        assertEquals("Text Test7", outputSeven.getTitle());
        assertEquals(dmock, outputSeven.getLaunchDate());
        assertEquals(0.00, outputSeven.getPrice());
        
        BookVO outputTwelve = outputList.get(12);
        
        assertEquals(12, outputTwelve.getKey());
        assertEquals("Authro Test12", outputTwelve.getAuthor());
        assertEquals("Text Test12", outputTwelve.getTitle());
        assertEquals(dmock, outputTwelve.getLaunchDate());
        assertEquals(0.00, outputTwelve.getPrice());
    }
    
    @Test
    public void parseVOToEntityTest()throws Exception {
    	Date dmock = sdf.parse("11/11/1111");
        Book output = DozerMapper.parseObject(inputObject.mockVO(),Book.class);
        
        assertEquals(0, output.getId());
        assertEquals("Authro Test0", output.getAuthor());
        assertEquals("Text Test0", output.getTitle());
        assertEquals(dmock, output.getLaunchDate());
        assertEquals(0.00, output.getPrice());
        
    }
    
    @Test
    public void parseVOListToEntityListTest()throws Exception {
    	Date dmock = sdf.parse("11/11/1111");
        List<Book> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Book.class);
        Book outputZero = outputList.get(0);
        
        assertEquals(0, outputZero.getId());
        assertEquals("Authro Test0", outputZero.getAuthor());
        assertEquals("Text Test0", outputZero.getTitle());
        assertEquals(dmock, outputZero.getLaunchDate());
        assertEquals(0.00, outputZero.getPrice());
        
        Book outputSeven = outputList.get(7);
        
        assertEquals(7, outputSeven.getId());
        assertEquals("Authro Test7", outputSeven.getAuthor());
        assertEquals("Text Test7", outputSeven.getTitle());
        assertEquals(dmock, outputSeven.getLaunchDate());
        assertEquals(0.00, outputSeven.getPrice());
        
        Book outputTwelve = outputList.get(12);
        
        assertEquals(12, outputTwelve.getId());
        assertEquals("Authro Test12", outputTwelve.getAuthor());
        assertEquals("Text Test12", outputTwelve.getTitle());
        assertEquals(dmock, outputTwelve.getLaunchDate());
        assertEquals(0.00, outputTwelve.getPrice());
    }



}