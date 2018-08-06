/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bs

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-06 13:29:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bs_major
-- ----------------------------
DROP TABLE IF EXISTS `bs_major`;
CREATE TABLE `bs_major` (
  `pk_major` int(11) NOT NULL AUTO_INCREMENT COMMENT '年级专业表主键',
  `major` varchar(20) NOT NULL COMMENT '专业',
  `grade` varchar(10) NOT NULL COMMENT '年级',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `last_updated_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pk_major`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_major
-- ----------------------------
INSERT INTO `bs_major` VALUES ('1', '软件工程', '2014', '1000000', '2018-01-24 16:19:30', '1000000', '2018-01-25 14:56:53');
INSERT INTO `bs_major` VALUES ('2', '金融学', '2014', '1000000', '2018-01-25 14:16:17', '1000000', '2018-01-25 14:16:17');
INSERT INTO `bs_major` VALUES ('5', '计算机', '2014', '1000000', '2018-02-01 18:45:54', '1000000', '2018-02-02 08:39:29');
INSERT INTO `bs_major` VALUES ('9', '审计', '2014', '1000000', '2018-02-02 08:39:35', '1000000', '2018-02-06 09:27:58');
INSERT INTO `bs_major` VALUES ('10', '投资', '2013', '1000000', '2018-02-02 08:39:37', '1000000', '2018-02-06 09:28:07');
INSERT INTO `bs_major` VALUES ('11', '舞蹈', '2014', '1000000', '2018-02-02 08:39:38', '1000000', '2018-02-06 09:30:33');
INSERT INTO `bs_major` VALUES ('12', '环境科学', '2013', '1000000', '2018-02-02 08:39:39', '1000000', '2018-02-06 09:28:30');
INSERT INTO `bs_major` VALUES ('13', '英语', '2013', '1000000', '2018-02-02 08:39:41', '1000000', '2018-02-06 09:29:11');
INSERT INTO `bs_major` VALUES ('14', '土木工程', '2014', '1000000', '2018-02-02 08:39:42', '1000000', '2018-02-06 09:28:50');
INSERT INTO `bs_major` VALUES ('15', '阿拉伯语', '2014', '1000000', '2018-02-02 08:39:43', '1000000', '2018-02-06 09:28:42');

-- ----------------------------
-- Table structure for bs_managers
-- ----------------------------
DROP TABLE IF EXISTS `bs_managers`;
CREATE TABLE `bs_managers` (
  `pk_manager` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员表主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` char(1) NOT NULL COMMENT '角色(0-管理员，1-教师，2-学生)',
  PRIMARY KEY (`pk_manager`)
) ENGINE=InnoDB AUTO_INCREMENT=1000002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_managers
-- ----------------------------
INSERT INTO `bs_managers` VALUES ('1000000', 'admin', 'admin', '0');

-- ----------------------------
-- Table structure for bs_notice
-- ----------------------------
DROP TABLE IF EXISTS `bs_notice`;
CREATE TABLE `bs_notice` (
  `pk_notice` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知表主键',
  `notice_content` varchar(500) DEFAULT NULL COMMENT '通知内容',
  `flag` char(1) DEFAULT NULL COMMENT '是否有效',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `last_updated_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pk_notice`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_notice
-- ----------------------------
INSERT INTO `bs_notice` VALUES ('10000', '这是一条测试通知！', 'Y', '1000000', '2017-12-29 11:18:59', '1000000', '2018-04-08 20:11:02');
INSERT INTO `bs_notice` VALUES ('10001', '所以，我们应该热烈庆祝一下', 'Y', '1000000', '2017-12-29 11:19:10', '1000000', '2017-12-29 14:17:36');
INSERT INTO `bs_notice` VALUES ('10002', '今天，我们完成了管理员对通知的管理功能', 'Y', '1000000', '2017-12-29 11:19:10', '1000000', '2017-12-29 14:16:42');
INSERT INTO `bs_notice` VALUES ('10003', '前面两条好像搞反了，没事，懂就行', 'Y', '1000000', '2017-12-29 14:07:39', '1000000', '2017-12-29 14:18:02');
INSERT INTO `bs_notice` VALUES ('10004', '通知显示出来吧！！', 'Y', '1000000', '2018-01-03 14:24:30', '1000000', '2018-01-03 15:11:15');
INSERT INTO `bs_notice` VALUES ('10005', '修改成功吧！！！', 'Y', '1000000', '2018-01-03 14:28:39', '1000000', '2018-01-03 15:08:09');
INSERT INTO `bs_notice` VALUES ('10006', '这次肯定成功！！', 'Y', '1000000', '2018-01-03 14:31:30', '1000000', '2018-01-03 14:31:30');
INSERT INTO `bs_notice` VALUES ('10007', '很完美！', 'Y', '1000000', '2018-01-03 14:32:07', '1000000', '2018-01-23 17:02:31');
INSERT INTO `bs_notice` VALUES ('10008', '这次肯定显示出来！！', 'Y', '1000000', '2018-01-03 14:58:44', '1000000', '2018-02-02 15:43:41');
INSERT INTO `bs_notice` VALUES ('10009', '历史性的一刻，datatable真的难用，垃圾插件!!', 'N', '1000000', '2018-01-03 15:22:06', '1000000', '2018-05-02 12:48:57');
INSERT INTO `bs_notice` VALUES ('10010', '12311', 'Y', '1000000', '2018-01-03 15:22:36', '1000000', '2018-01-22 11:29:17');
INSERT INTO `bs_notice` VALUES ('10011', '完成，撒花~~~', 'Y', '1000000', '2018-01-03 15:23:33', '1000000', '2018-01-23 17:15:06');
INSERT INTO `bs_notice` VALUES ('10012', '结束~~~~~~', 'Y', '1000000', '2018-01-04 10:42:18', '1000000', '2018-03-08 16:18:09');
INSERT INTO `bs_notice` VALUES ('10013', '测试插件', 'Y', '1000000', '2018-02-02 17:31:33', '1000000', '2018-03-08 16:18:36');

-- ----------------------------
-- Table structure for bs_paper
-- ----------------------------
DROP TABLE IF EXISTS `bs_paper`;
CREATE TABLE `bs_paper` (
  `pk_paper` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷表主键',
  `paper_name` varchar(100) NOT NULL COMMENT '试卷名称',
  `flag_public` char(1) NOT NULL COMMENT '试卷公开状态（Y-公开；N-私有）',
  `flag_edit` char(1) NOT NULL COMMENT '试卷编辑状态（Y-可编辑；N-不可编辑）',
  `flag` char(1) NOT NULL COMMENT '是否有效',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `last_updated_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pk_paper`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_paper
-- ----------------------------
INSERT INTO `bs_paper` VALUES ('1', '系统测试试卷', 'Y', 'Y', 'Y', '2', '2018-03-20 18:20:55', '2', '2018-03-20 18:20:55');
INSERT INTO `bs_paper` VALUES ('2', '系统测试新增试卷', 'Y', 'Y', 'Y', '2', '2018-03-26 17:17:16', '2', '2018-03-26 17:17:16');
INSERT INTO `bs_paper` VALUES ('3', '自动组卷测试', 'Y', 'Y', 'Y', '2', '2018-03-28 19:44:52', '2', '2018-03-28 19:44:52');
INSERT INTO `bs_paper` VALUES ('4', '自动组卷', 'N', 'Y', 'N', '2', '2018-03-28 19:51:16', '2', '2018-03-28 19:51:16');
INSERT INTO `bs_paper` VALUES ('5', '自动组卷001', 'Y', 'Y', 'Y', '2', '2018-03-28 19:58:24', '2', '2018-03-28 19:58:24');
INSERT INTO `bs_paper` VALUES ('6', '自动组卷测试002', 'Y', 'Y', 'N', '2', '2018-03-28 20:07:26', '2', '2018-03-28 20:07:26');
INSERT INTO `bs_paper` VALUES ('7', '软件工程2018期末考试', 'Y', 'Y', 'Y', '2', '2018-04-03 21:41:59', '2', '2018-04-03 21:41:59');
INSERT INTO `bs_paper` VALUES ('8', '2018期末', 'Y', 'Y', 'Y', '2', '2018-04-04 11:15:11', '2', '2018-04-04 11:15:11');
INSERT INTO `bs_paper` VALUES ('9', '2018期末二', 'Y', 'Y', 'Y', '2', '2018-04-04 11:16:34', '2', '2018-04-04 11:16:34');
INSERT INTO `bs_paper` VALUES ('10', '2018秋季考试', 'Y', 'Y', 'Y', '2', '2018-05-02 14:26:06', '2', '2018-05-02 14:26:06');

-- ----------------------------
-- Table structure for bs_paper_detail
-- ----------------------------
DROP TABLE IF EXISTS `bs_paper_detail`;
CREATE TABLE `bs_paper_detail` (
  `pk_paper_detail` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷详情表主键',
  `fk_paper` int(11) NOT NULL COMMENT '试卷表外键',
  `fk_tests` int(11) NOT NULL COMMENT '题目表外键',
  `tests_type` char(1) NOT NULL COMMENT '试题类型(1-选择题，2-判断题，3-填空题)',
  `score` varchar(3) DEFAULT NULL COMMENT '分值',
  `priority` varchar(3) DEFAULT '1' COMMENT '优先级（数字越大越优先，最高999）',
  PRIMARY KEY (`pk_paper_detail`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_paper_detail
-- ----------------------------
INSERT INTO `bs_paper_detail` VALUES ('1', '1', '10000', '1', '2', '1');
INSERT INTO `bs_paper_detail` VALUES ('2', '1', '10001', '1', '2', '2');
INSERT INTO `bs_paper_detail` VALUES ('10', '1', '10003', '1', '2', '5');
INSERT INTO `bs_paper_detail` VALUES ('11', '1', '10004', '1', '2', '3');
INSERT INTO `bs_paper_detail` VALUES ('12', '2', '10002', '1', '5', '5');
INSERT INTO `bs_paper_detail` VALUES ('13', '2', '10000', '1', '5', '6');
INSERT INTO `bs_paper_detail` VALUES ('14', '5', '10001', '1', '3', '1');
INSERT INTO `bs_paper_detail` VALUES ('15', '5', '10000', '1', '3', '1');
INSERT INTO `bs_paper_detail` VALUES ('16', '5', '10003', '1', '3', '1');
INSERT INTO `bs_paper_detail` VALUES ('17', '6', '10002', '1', '20', '1');
INSERT INTO `bs_paper_detail` VALUES ('18', '6', '10004', '1', '20', '1');
INSERT INTO `bs_paper_detail` VALUES ('19', '6', '10000', '1', '20', '1');
INSERT INTO `bs_paper_detail` VALUES ('20', '6', '10001', '1', '20', '1');
INSERT INTO `bs_paper_detail` VALUES ('21', '6', '10003', '1', '20', '1');
INSERT INTO `bs_paper_detail` VALUES ('22', '7', '10000', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('23', '7', '10002', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('24', '7', '10011', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('25', '7', '10004', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('26', '7', '10003', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('27', '7', '10001', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('28', '8', '10000', '1', '5', '5');
INSERT INTO `bs_paper_detail` VALUES ('29', '8', '10004', '1', '5', '5');
INSERT INTO `bs_paper_detail` VALUES ('30', '9', '10000', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('31', '9', '10002', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('32', '9', '10004', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('33', '9', '10011', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('34', '9', '10001', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('35', '10', '10011', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('36', '10', '10002', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('37', '10', '10004', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('38', '10', '10001', '1', '5', '1');
INSERT INTO `bs_paper_detail` VALUES ('39', '10', '10003', '1', '5', '1');

-- ----------------------------
-- Table structure for bs_rel_paper_major
-- ----------------------------
DROP TABLE IF EXISTS `bs_rel_paper_major`;
CREATE TABLE `bs_rel_paper_major` (
  `pk_rel_paper_major` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表主键（试卷&专业）',
  `fk_paper` int(11) NOT NULL COMMENT '试卷表外键',
  `fk_major` int(11) NOT NULL COMMENT '专业表外键',
  `publish_time` datetime DEFAULT NULL COMMENT '布置试卷时间',
  PRIMARY KEY (`pk_rel_paper_major`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_rel_paper_major
-- ----------------------------
INSERT INTO `bs_rel_paper_major` VALUES ('1', '1', '1', '2018-04-02 21:47:18');
INSERT INTO `bs_rel_paper_major` VALUES ('2', '7', '1', '2018-04-03 21:42:22');
INSERT INTO `bs_rel_paper_major` VALUES ('3', '2', '1', '2018-04-03 22:45:29');
INSERT INTO `bs_rel_paper_major` VALUES ('4', '9', '1', '2018-04-04 11:18:18');
INSERT INTO `bs_rel_paper_major` VALUES ('5', '10', '1', '2018-05-02 14:26:19');

-- ----------------------------
-- Table structure for bs_rel_teacher_major
-- ----------------------------
DROP TABLE IF EXISTS `bs_rel_teacher_major`;
CREATE TABLE `bs_rel_teacher_major` (
  `pk_rel_teacher_major` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表主键（教师&专业）',
  `fk_teacher` int(11) NOT NULL COMMENT '教师表外键',
  `fk_major` int(11) NOT NULL COMMENT '专业表外键',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `last_updated_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pk_rel_teacher_major`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_rel_teacher_major
-- ----------------------------
INSERT INTO `bs_rel_teacher_major` VALUES ('1', '2', '1', '1000000', '2018-02-02 14:06:19', '1000000', '2018-02-02 14:06:19');
INSERT INTO `bs_rel_teacher_major` VALUES ('2', '2', '2', '1000000', '2018-02-02 14:06:32', '1000000', '2018-02-02 14:06:32');
INSERT INTO `bs_rel_teacher_major` VALUES ('3', '1', '2', '1000000', '2018-02-06 10:01:34', '1000000', '2018-02-06 10:01:34');
INSERT INTO `bs_rel_teacher_major` VALUES ('5', '3', '12', '1000000', '2018-02-06 15:36:00', '1000000', '2018-02-06 15:36:00');
INSERT INTO `bs_rel_teacher_major` VALUES ('6', '1', '1', '1000000', '2018-02-06 19:12:45', '1000000', '2018-02-06 19:12:45');

-- ----------------------------
-- Table structure for bs_score
-- ----------------------------
DROP TABLE IF EXISTS `bs_score`;
CREATE TABLE `bs_score` (
  `pk_score` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩表主键',
  `fk_student` int(11) NOT NULL COMMENT '学生表外键',
  `fk_paper` int(11) NOT NULL COMMENT '试卷表外键',
  `score` varchar(3) DEFAULT NULL COMMENT '成绩',
  `flag` char(1) DEFAULT NULL COMMENT '状态（Y-已完成；N-未完成）',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`pk_score`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_score
-- ----------------------------
INSERT INTO `bs_score` VALUES ('1', '2', '1', '4', 'Y', '2018-04-03 22:43:22');
INSERT INTO `bs_score` VALUES ('2', '2', '2', '5', 'Y', '2018-04-03 22:46:49');
INSERT INTO `bs_score` VALUES ('3', '2', '9', '0', 'Y', '2018-04-04 11:19:28');
INSERT INTO `bs_score` VALUES ('4', '2', '7', '15', 'Y', '2018-05-02 13:09:29');

-- ----------------------------
-- Table structure for bs_student
-- ----------------------------
DROP TABLE IF EXISTS `bs_student`;
CREATE TABLE `bs_student` (
  `pk_student` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生表主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `student_id` varchar(20) NOT NULL COMMENT '学号',
  `fk_major` int(11) NOT NULL COMMENT '年级专业表外键',
  `role` char(1) NOT NULL DEFAULT '2' COMMENT '角色(0-管理员，1-教师，2-学生)',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `last_updated_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pk_student`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_student
-- ----------------------------
INSERT INTO `bs_student` VALUES ('1', 'lwj', '0F6EF21F45F9A979235B2C231B18B0C4', null, null, '罗文佳', '2014010100001', '2', '2', '1000000', '2018-01-16 19:01:33', '1000000', '2018-02-06 19:24:16');
INSERT INTO `bs_student` VALUES ('2', 'zjf', '5BAE1FCBBAC560DB99D70426ECFEE428', 'zjf', 'zjf', '张靖烽', '2014020300057', '1', '2', '1000000', '2018-01-16 19:11:46', '1000000', '2018-02-06 20:02:15');
INSERT INTO `bs_student` VALUES ('3', 'xxq', '5299491652C9A4D45583DCAEEC05395F', null, null, '小小七', '2014010100002', '13', '2', '1000000', '2018-02-05 18:48:32', '1000000', '2018-02-06 20:02:24');

-- ----------------------------
-- Table structure for bs_teacher
-- ----------------------------
DROP TABLE IF EXISTS `bs_teacher`;
CREATE TABLE `bs_teacher` (
  `pk_teacher` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师表主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `name` varchar(10) NOT NULL COMMENT '教师姓名',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `role` char(1) NOT NULL DEFAULT '1' COMMENT '角色(0-管理员，1-教师，2-学生)',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `last_updated_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pk_teacher`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_teacher
-- ----------------------------
INSERT INTO `bs_teacher` VALUES ('1', 'lwj', '0F6EF21F45F9A979235B2C231B18B0C4', null, null, '罗文佳', '15651001234', '1', '1000000', '2018-01-16 19:37:14', '1000000', '2018-01-16 19:37:14');
INSERT INTO `bs_teacher` VALUES ('2', 'zjf', '5BAE1FCBBAC560DB99D70426ECFEE428', '青夕夕', '青青夕', '张靖烽', '15651001658', '1', '1000000', '2018-01-16 20:09:46', '2', '2018-03-06 19:52:23');
INSERT INTO `bs_teacher` VALUES ('3', 'xxq', '5299491652C9A4D45583DCAEEC05395F', null, null, '小小七', '13890971234', '1', '1000000', '2018-01-31 16:46:31', '1000000', '2018-01-31 16:46:31');
INSERT INTO `bs_teacher` VALUES ('4', 'cs', '705D6A2118A42958B90790266AC7317B', null, null, 'cs', '13890986542', '1', '1000000', '2018-04-08 20:20:39', '1000000', '2018-04-08 20:20:39');

-- ----------------------------
-- Table structure for bs_tests
-- ----------------------------
DROP TABLE IF EXISTS `bs_tests`;
CREATE TABLE `bs_tests` (
  `pk_test` int(11) NOT NULL AUTO_INCREMENT COMMENT '试题表主键',
  `test_type` char(1) NOT NULL COMMENT '试题类型(1-选择题，2-判断题，3-填空题)',
  `test_subject` varchar(50) NOT NULL COMMENT '试题所属学科',
  `test_title` varchar(1000) NOT NULL COMMENT '试题题目',
  `test_content` varchar(4000) NOT NULL COMMENT '试题内容',
  `test_answer` varchar(1000) NOT NULL COMMENT '试题答案',
  `test_analyze` varchar(4000) DEFAULT NULL COMMENT '试题解析',
  `flag` char(1) NOT NULL COMMENT '是否有效',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `last_updated_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pk_test`)
) ENGINE=InnoDB AUTO_INCREMENT=10012 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_tests
-- ----------------------------
INSERT INTO `bs_tests` VALUES ('10000', '1', 'Java', '关于sleep()和wait()，以下描述错误的一项是( )', 'sleep是线程类（Thread）的方法，wait是Object类的方法;sleep不释放对象锁，wait放弃对象锁;sleep暂停线程、但监控状态仍然保持，结束后会自动恢复;wait后进入等待锁定池，只有针对此对象发出notify方法后获得对象锁进入运行状态', 'D', 'sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。', 'Y', '2', '2018-03-08 14:34:12', '2', '2018-03-08 14:34:12');
INSERT INTO `bs_tests` VALUES ('10001', '1', 'Java', '将类的成员的访问权限设置为默认的，则该成员能被( )', '同一包中的类访问;其它包中的类访问;所有的类访问;所有的类的子类访问', 'A', 'public:可以被所有其他类所访问；protected:自身、子类及同一个包中类可以访问；default:同一包中的类可以访问；private:只能被自己访问和修改。', 'Y', '2', '2018-03-12 13:06:08', '2', '2018-03-12 13:06:08');
INSERT INTO `bs_tests` VALUES ('10002', '1', 'Java', '关于中间件特点的描述.不正确的是（）', '中间件运行于客户机/服务器的操作系统内核中，提高内核运行效率;中间件应支持标准的协议和接口;中间件可运行于多种硬件和操作系统平台上;跨越网络,硬件，操作系统平台的应用或服务可通过中间件透明交互', 'A', '中间件是一种独立的系统软件或服务程序，分布式应用软件借助这种软件在不同的技术之间共享资源。中间件位于客户机/ 服务器的操作系统之上，管理计算机资源和网络通讯。是连接两个独立应用程序或独立系统的软件。相连接的系统，即使它们具有不同的接口，但通过中间件相互之间仍能交换信息。执行中间件的一个关键途径是信息传递。通过中间件，应用程序可以工作于多平台或OS环境。', 'Y', '2', '2018-03-12 13:44:44', '2', '2018-03-12 13:44:44');
INSERT INTO `bs_tests` VALUES ('10003', '1', 'Java', '下面有关java实例变量,局部变量,类变量和final变量的说法，错误的是？', '实例变量指的是类中定义的变量，即成员变量，如果没有初始化，会有默认值;局部变量指的是在方法中定义的变量，如果没有初始化，会有默认值;类变量指的是用static修饰的属性;final变量指的是用final 修饰的变量', 'B', '局部变量是指类方法中的变量，必须初始化。局部变量运行时被分配在栈中，量大，生命周期短，如果虚拟机给每个局部变量都初始化一下，是一笔很大的开销，但变量不初始化为默认值就使用是不安全的。出于速度和安全性两个方面的综合考虑，解决方案就是虚拟机不初始化，但要求编写者一定要在使用前给变量赋值。', 'Y', '2', '2018-03-12 13:45:11', '2', '2018-03-12 13:45:11');
INSERT INTO `bs_tests` VALUES ('10004', '1', 'Java', '下面有关servlet service描述错误的是？', '不管是post还是get方法提交过来的连接，都会在service中处理;doGet/doPost 则是在 javax.servlet.GenericServlet 中实现的;service()是在javax.servlet.Servlet接口中定义的;service判断请求类型，决定是调用doGet还是doPost方法', 'B', 'doGet/doPost 则是在 javax.servlet.http.HttpServlet 中实现的', 'Y', '2', '2018-03-12 13:51:25', '2', '2018-03-12 13:51:25');
INSERT INTO `bs_tests` VALUES ('10005', '1', '测试题目', '111111111111111111111111111111', '的得分财务处VR;跟她还要填还让他雨能停;额外服务费热女童;VR饿啊哥哥BT二倍体', 'C', '发热发个共同日本', 'Y', '2', '2018-03-20 19:09:38', '2', '2018-03-20 19:09:38');
INSERT INTO `bs_tests` VALUES ('10006', '1', '测试题目', '你家住哪里', '成都;北京;重庆;上海', 'B', '户口本', 'Y', '2', '2018-03-20 19:10:30', '2', '2018-03-20 19:10:30');
INSERT INTO `bs_tests` VALUES ('10007', '1', '测试题目', '你家住哪里1', '成都1;北京1;重庆1;上海1', 'C', '户口本', 'Y', '2', '2018-03-20 19:10:46', '2', '2018-03-20 19:10:46');
INSERT INTO `bs_tests` VALUES ('10008', '1', '测试题目', '你家住哪里12', '成都12;北京12;重庆12;上海12', 'C', '户口本', 'Y', '2', '2018-03-20 19:10:50', '2', '2018-03-20 19:10:50');
INSERT INTO `bs_tests` VALUES ('10009', '1', '测试题目', '你家住哪里123', '成都123;北京123;重庆123;上海123', 'C', '户口本', 'Y', '2', '2018-03-20 19:10:55', '2', '2018-03-20 19:10:55');
INSERT INTO `bs_tests` VALUES ('10010', '1', '测试题目', '啊啊啊啊啊啊啊啊', '啊啊啊啊啊啊;啊啊啊啊啊啊;啊啊啊啊啊啊;啊啊啊啊啊啊', 'C', '啊啊啊啊啊啊', 'Y', '2', '2018-03-20 19:11:11', '2', '2018-03-20 19:11:11');
INSERT INTO `bs_tests` VALUES ('10011', '1', 'Java', '下列说法中哪项是错误的是（）', '不同CPU的计算机有不同的机器语言和汇编语言;回收站是硬盘中的一块区域，而剪贴板是内存中的一块区域;在Dos中可以用的文件名在windows 98中一定可用;计算机病毒不可能会驻留在BIOS中', 'D', 'BIOS是英文”Basic Input Output System”的缩略语，直译过来后中文名称就是”基本输入输出系统”。其实，它是一组固化到计算机内主板上一个ROM芯片上的程序，它保存着计算机最重要的基本输入输出的程序、系统设置信息、开机后自检程序和系统自启动程序。 其主要功能是为计算机提供最底层的、最直接的硬件设置和控制。', 'Y', '2', '2018-04-03 21:41:08', '2', '2018-04-03 21:41:08');
