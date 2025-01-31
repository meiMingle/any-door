package io.github.lgp547.anydoor.test.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.lgp547.anydoor.test.dto.Role;
import io.github.lgp547.anydoor.test.dto.User;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import static io.github.lgp547.anydoor.test.util.AssertUtil.assertIsNull;
import static io.github.lgp547.anydoor.test.util.AssertUtil.assertIsTrue;
import static io.github.lgp547.anydoor.test.util.AssertUtil.assertNotNull;

@RestController
public class Bean {

    public static final Integer id = 1;

    public static final String name = "AnyDoorUserName";

    public static final User user = new User(1, "AnyDoorUserName1");

    public static final List<String> strings = new ArrayList<String>() {{
        add("AnyDoorStr1");
        add("AnyDoorStr2");
        add("AnyDoorStr1");
    }};

    public static final List<User> users = new ArrayList<User>() {{
        add(new User(2, "2"));
        add(new User(3, "3"));
    }};

    public static final List<Long> longs = new ArrayList<Long>() {{
        add(1L);
        add(2L);
        add(3L);
    }};

    public static final String emptyUser = "";

    public static final String emptyNum = "0";

    public static final Role role = new Role(100L, "roleName", users);

//    public static final Long dateTimeByLong = 1L;

    public static final String dateTimeByStr = "2023-01-01T00:00:00";

    public static JsonNode getContent() {
        ObjectNode jsonNode = new ObjectNode(JsonNodeFactory.instance);
        jsonNode.put("id", id);
        jsonNode.put("name", name);
        jsonNode.putPOJO("user", user);
        jsonNode.putPOJO("strings", strings);
        jsonNode.putPOJO("users", users);
        jsonNode.put("emptyUser", emptyUser);
        jsonNode.put("emptyNum", emptyNum);
        String s = null;
        jsonNode.put("nameNull", s);
        jsonNode.putPOJO("role", role);
        jsonNode.put("func", "(a,b) -> a + b");
        jsonNode.putPOJO("a", "Hello");
        jsonNode.putPOJO("b", " World");
//        jsonNode.put("dateTimeByLong", dateTimeByLong);
        jsonNode.put("dateTimeByStr", dateTimeByStr);
        jsonNode.putPOJO("longs", longs);
        return jsonNode;
    }

    private void noParamPrivate() {
        System.out.println("noParamPrivate");
    }

    public static void noParamStatic() {
        System.out.println("noParamStatic");
    }

    public void noParam() {
        System.out.println("noParam");
    }

    public String only(String name) {
        System.out.println(name);
        return name;
    }

    public void test(Integer num, String name) {
        System.out.println("num:" + num);
        System.out.println("name:" + name);
    }

    public String oneParam(String name) {
        assertIsTrue(Bean.name.equals(name));
        return name;
    }

    public void oneParamNull(String nameNull) {
        Assert.isNull(nameNull);
    }

    private String oneParamPrivate(String name) {
        assertIsTrue(Bean.name.equals(name));
        return name;
    }

    public Long oneParam(Long nullParam) {
        assertIsNull(nullParam);
        return nullParam;
    }

    public Integer oneParamEmpty(Integer emptyNum) {
        assertNotNull(emptyNum);
        return emptyNum;
    }

    public User oneParamEmpty(User emptyUser) {
        assertNotNull(emptyUser);
        assertIsNull(emptyUser.getId());
        assertIsNull(emptyUser.getName());
        return emptyUser;
    }

    public User oneParam(User user) {
        assertIsTrue(Bean.user.equals(user));
        return user;
    }

    public List<String> oneParam1(List<String> strings) {
        assertIsTrue(Bean.strings.equals(strings));
        return strings;
    }

    public Set<String> oneParam1Set(Set<String> strings) {
        HashSet<String> set = new HashSet<>(Bean.strings);
        assertIsTrue(set.equals(strings));
        return strings;
    }

    public List<User> oneParam2(List<User> users) {
        Objects.requireNonNull(users);
        for (int i = 0; i < Bean.users.size(); i++) {
            assertIsTrue(Bean.users.get(i).equals(users.get(i)));
        }
        return users;
    }

    public JsonNode multipleParam(String name, Integer id) {
        assertIsTrue(Bean.name.equals(name));
        assertIsTrue(Bean.id.equals(id));
        ObjectNode jsonNode = new ObjectNode(JsonNodeFactory.instance);
        jsonNode.put("name", name);
        jsonNode.put("id", id);
        return jsonNode;
    }

    public JsonNode multipleParam(Integer id, String name) {
        assertIsTrue(Bean.name.equals(name));
        assertIsTrue(Bean.id.equals(id));
        ObjectNode jsonNode = new ObjectNode(JsonNodeFactory.instance);
        jsonNode.put("name", name);
        jsonNode.put("id", id);
        return jsonNode;
    }

    public void exception() {
        System.out.println("exception");
        throw new RuntimeException("test exception");
    }

    public void role(Role role) {
        assertIsTrue(Bean.role.equals(role));
    }

    /**
     * 重载的私有方法
     */
    private void role() {
        System.out.println("执行重载的私有方法");
    }

    /**
     * 测试转LDT
     */
    public void testDate(LocalDateTime dateTimeByLong, LocalDateTime dateTimeByStr) {
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.dateTimeByLong), ZoneId.systemDefault());
//        Assert.isTrue(Objects.equals(dateTimeByLong, localDateTime));

        LocalDateTime localDateTime1 = LocalDateTime.parse(Bean.dateTimeByStr, DateTimeFormatter.ISO_DATE_TIME);
        Assert.isTrue(Objects.equals(dateTimeByStr, localDateTime1));

    }

    public void listInt(List<Long> longs) {
        assertIsTrue(Bean.longs.equals(longs));
    }
}
