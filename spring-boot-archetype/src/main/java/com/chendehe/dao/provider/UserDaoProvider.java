package com.chendehe.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * provider.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
public final class UserDaoProvider {

    private static final String TABLE_NAME = "t_user";

    public String listByPage() {
        return new SQL() {
            {
                SELECT("*").FROM(TABLE_NAME).ORDER_BY("create_time").LIMIT("#{page.pageSize}")
                    .OFFSET("#{page.currentPage}");
            }
        }.toString();
    }

    public String getById() {
        return new SQL() {
            {
                SELECT("*").FROM(TABLE_NAME).WHERE("id = #{id}");
            }
        }.toString();
    }

    public String save() {
        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME)
                    .INTO_VALUES("#{id}, #{name}, #{gender}, #{birthday}, #{address}, #{createTime}, #{updateTime}");
            }
        }.toString();
    }

    public String saveBatch(Map<String, Object> map) {
        Object users = map.get("users");
        if (users instanceof List) {
            List userList = (List)map.get("users");
            if (CollectionUtils.isEmpty(userList)) {
                return "";
            }

            MessageFormat mf = new MessageFormat("(#'{'users[{0}].id}, #'{'users[{0}].name}, #'{'users[{0}].gender}"
                + ", #'{'users[{0}].birthday}, #'{'users[{0}].address}"
                + ", #'{'users[{0}].createTime}, #'{'users[{0}].updateTime})");

            StringBuilder builder = new StringBuilder();
            builder.append("INSERT INTO " + TABLE_NAME + " VALUES ");

            for (int i = 0; i < userList.size(); i++) {
                builder.append(mf.format(new Object[] {i}));
                if (i < userList.size() - 1) {
                    builder.append(",");
                }
            }
            return builder.toString();

        }

        return "";
    }

    public String update() {
        return new SQL() {
            {
                UPDATE(TABLE_NAME).SET("name = #{name}, gender = #{gender}, birthday = #{birthday}, "
                    + "address = #{address}, update_time = #{updateTime}").WHERE("id = #{id}");
            }
        }.toString();
    }

    public String remove() {
        return new SQL() {
            {
                DELETE_FROM(TABLE_NAME).WHERE("id = #{id}");
            }
        }.toString();
    }

    public String removeByIds(Map<String, Object> map) {
        Object ids = map.get("ids");
        if (ids instanceof Set) {
            Set userIds = (Set)ids;
            if (CollectionUtils.isEmpty(userIds)) {
                return "";
            }

            return new SQL() {
                {
                    DELETE_FROM(TABLE_NAME)
                        .WHERE("id IN " + "('" + String.join("','", userIds) + "')");
                }
            }.toString();
        }
        return "";
    }

    public String count() {
        return new SQL() {
            {
                SELECT("COUNT(*)").FROM(TABLE_NAME);
            }
        }.toString();
    }
}
