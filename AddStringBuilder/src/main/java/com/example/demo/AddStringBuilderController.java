package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AddStringBuilderController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "SQLを入力してください。");    // 表示メッセージ
		return mav;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(@RequestParam("sql")String sql,
			ModelAndView mav) {
		mav.setViewName("index");

		// 入力されたSQLを改行コードで分割してリストに格納したあとに、StringBuilderの文字列を付加する
		List<String> sqlList = Arrays.asList(sql.split("\r\n"));
		StringBuilder sbSql  = new StringBuilder("StringBuilder sql  = new StringBuilder();\r\n");
		sqlList.stream().forEach(str -> sbSql.append("sql.append(\"" + str + "\");\r\n"));

		mav.addObject("result", sbSql.toString());
		return mav;
	}
}
