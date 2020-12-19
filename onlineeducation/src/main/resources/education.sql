
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `apassword` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `atoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录token值',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '9c30ecdff65e86e1bf07da19e2f3adaf');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `tid` bigint(11) NOT NULL COMMENT '发布课程的教师id',
  `uploadTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '发布课程时间',
  `cimageURL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
  `cdescription` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程简介',
  `is_checked` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未审核' COMMENT '课程审核状态',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE,
  INDEX `cname`(`cname`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (2, '数据结构', 1, '2020-12-11 15:52:22', '/files/20201211_16023432.jpg', '这门课有点难噢', '通过', 0);
INSERT INTO `course` VALUES (3, 'web前端', 1, '2020-12-11 16:03:27', '/files/20201211_16032511.jpg', '这门课还好', '通过', 0);
INSERT INTO `course` VALUES (4, '计算机组成原理', 2, '2020-12-11 16:25:34', '/files/20201211_1625237.png', '这门课超级难噢', '通过', 0);
INSERT INTO `course` VALUES (6, '操作系统', 2, '2020-12-12 18:07:16', '/files/20201212_1807003.jpg', '这门课也不会很难噢', '通过', 0);

-- ----------------------------
-- Table structure for courseware
-- ----------------------------
DROP TABLE IF EXISTS `courseware`;
CREATE TABLE `courseware`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课件名称',
  `teacher_ID` bigint(4) NOT NULL COMMENT '发布课件的教师id',
  `uploadTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '课件上传时间',
  `coursewareURL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课件上传路径',
  `courseName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属课程名称',
  `is_checked` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未审核' COMMENT '课件审核状态',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teacher_ID`(`teacher_ID`) USING BTREE,
  INDEX `courseName`(`courseName`) USING BTREE,
  CONSTRAINT `courseware_ibfk_1` FOREIGN KEY (`teacher_ID`) REFERENCES `teacher` (`tid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `courseware_ibfk_2` FOREIGN KEY (`courseName`) REFERENCES `course` (`cname`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courseware
-- ----------------------------
INSERT INTO `courseware` VALUES (1, '第一章：常用的html标签', 1, '2020-12-11 16:06:22', '/files/20201211_16060798.pptx', 'web前端', '通过', 0);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `releaseTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '发布评论时间',
  `course_id` bigint(11) NOT NULL COMMENT '所属课程id',
  `parent_id` bigint(11) NULL DEFAULT NULL COMMENT '父级评论id',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论/回复人姓名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (2, '好难噢', '2020-12-11 16:44:14', 2, NULL, 0, '刘小李');

-- ----------------------------
-- Table structure for stu_course
-- ----------------------------
DROP TABLE IF EXISTS `stu_course`;
CREATE TABLE `stu_course`  (
  `cid` bigint(11) NOT NULL COMMENT '课程id',
  `sid` bigint(11) NOT NULL COMMENT '学生id'
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stu_course
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `spassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生登录密码',
  `studentID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生学号',
  `ssex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `sclass` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生班级',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '0未删除，1已删除',
  `stoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录token值',
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '李小晨', 'e10adc3949ba59abbe56e057f20f883e', '205524', '男', '软件2班', 0, '10bb1283b4481dfdd38302db63328884');
INSERT INTO `student` VALUES (2, '刘小李', 'e10adc3949ba59abbe56e057f20f883e', '205525', '男', '计科1602', 0, '5873316389caf568e4a2cae75e5c4bac');
INSERT INTO `student` VALUES (3, '管小冰', 'e10adc3949ba59abbe56e057f20f883e', '205555', '男', '计科1602', 0, '82614c0fa844e53272ac8e4f49c2a7ac');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `tname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师姓名',
  `tpassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师登录密码',
  `tJobNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师工号',
  `tsex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师性别',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0,
  `ttoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '倪小剑', 'e10adc3949ba59abbe56e057f20f883e', '202220', '男', 0, '9c354199c203fb195552d177a4443c92');
INSERT INTO `teacher` VALUES (2, '谢小德', 'e10adc3949ba59abbe56e057f20f883e', '202221', '男', 0, 'e6174cb8f3230fb76ba45a431c7fa459');
INSERT INTO `teacher` VALUES (3, '陈小刚', 'e10adc3949ba59abbe56e057f20f883e', '202222', '男', 0, NULL);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `test_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试题目',
  `option_A` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项A',
  `option_B` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项B',
  `option_C` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'C',
  `option_D` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'D',
  `answer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正确答案选项',
  `score` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分值',
  `courseName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属课程名称',
  `is_checked` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未审核' COMMENT '测试审核状态',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0,
  `tid` bigint(11) NOT NULL COMMENT '上传教师id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `courseName`(`courseName`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE,
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`courseName`) REFERENCES `course` (`cname`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `test_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '、数据结构是一门研究非数值计算的程序设计问题中计算机的数据元素以及它们之间的(   )和运算等的学科。', '结构', '关系', '运算', '算法', 'B', '10', '数据结构', '通过', 0, 1);
INSERT INTO `test` VALUES (2, '栈的特点是', '先进先出', '后进先出', '进了不出', '进了就出', 'B', '10', '数据结构', '通过', 0, 1);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `vid` bigint(4) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `vname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '视频名称',
  `teacherID` bigint(4) NOT NULL COMMENT '上传视频的教师id',
  `uploadTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '视频上传时间',
  `videoURL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '视频上传路径',
  `courseName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属课程名称',
  `is_checked` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未审核' COMMENT '视频审核状态',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`vid`) USING BTREE,
  INDEX `teacherID`(`teacherID`) USING BTREE,
  INDEX `courseName`(`courseName`) USING BTREE,
  CONSTRAINT `video_ibfk_1` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`tid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `video_ibfk_2` FOREIGN KEY (`courseName`) REFERENCES `course` (`cname`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, '1.1数据结构的基本概念', 1, '2020-12-11 16:04:10', '/files/20201211_16040211.mp4', '数据结构', '通过', 0);
INSERT INTO `video` VALUES (2, '1.2算法和算法评价01', 1, '2020-12-11 16:04:30', '/files/20201211_16042181.mp4', '数据结构', '通过', 0);
INSERT INTO `video` VALUES (3, '2.1 线性表的定义和基本操作', 1, '2020-12-11 16:04:55', '/files/20201211_1604512.mp4', '数据结构', '通过', 0);
INSERT INTO `video` VALUES (4, '01 - 常用标签', 1, '2020-12-11 16:05:33', '/files/20201211_16051944.mp4', 'web前端', '通过', 0);
INSERT INTO `video` VALUES (5, '02 - 图片标签', 1, '2020-12-11 16:05:53', '/files/20201211_16054960.mp4', 'web前端', '通过', 0);
INSERT INTO `video` VALUES (6, '1.1 计算机发展历程03', 2, '2020-12-11 16:26:05', '/files/20201211_16255856.mp4', '计算机组成原理', '通过', 0);
INSERT INTO `video` VALUES (7, '1.2 计算机系统层次结构01', 2, '2020-12-11 16:26:20', '/files/20201211_16261758.mp4', '计算机组成原理', '通过', 0);

SET FOREIGN_KEY_CHECKS = 1;
