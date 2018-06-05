package com.elex.oa.view.control;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.stereotype.Component;

@Component
public class MiniRadioButtonListViewHandler extends MiniBoxListViewHandler {
    public MiniRadioButtonListViewHandler() {
    }

    public String getPluginName() {
        return "mini-radiobuttonlist";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        super.parse(el, params, jsonObj);
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        String from = el.attr("from");
        if("sql".equals(from)) {
            String sqlKey = el.attr("sql");
            if(StringUtils.isNotEmpty(sqlKey)) {
                String url = (String)params.get("ctxPath") + "/sys/db/sysSqlCustomQuery/queryForJson_" + sqlKey + ".do";
                el.attr("url", url);
            }
        }

        if(!StringUtils.isEmpty(val)) {
            el.attr("value", val);
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name + "_name");
        el.replaceWith((new Element(Tag.valueOf("span"), "")).text(val));
    }
}