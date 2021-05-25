CREATE TABLE `student` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `name` varchar(120) NOT NULL,
  `status` varchar(120) NOT NULL,
  `major` varchar(120) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `student` (`id`, `name`, `status`, `major`) VALUES
(112, 'Thomas', 'Full time', 'Software engineer'),
(114, 'Frank', 'Part time', 'Software development');

CREATE TABLE `subject` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `prerequisite` varchar(120) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `subject` (`id`, `name`, `prerequisite`) VALUES
(1, 'Networking', 'System implementation');

CREATE TABLE `enrolment` (
   `id` int(11) AUTO_INCREMENT NOT NULL,
   `student_id` int(11) NOT NULL,
   `subject_id` int(11) NOT NULL,
   PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `enrolment` (`id`, `student_id`, `subject_id`) VALUES
(1, 112, 1), (2, 114, 1);