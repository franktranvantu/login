CREATE TABLE `user` (
   `id` int(11) AUTO_INCREMENT NOT NULL,
   `username` varchar(120) NOT NULL,
   `password` varchar(120) NOT NULL,
   `level` int(11) NOT NULL,
   PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (`id`, `username`, `password`, `level`) VALUES
(1, 'thomas', 'thomas123', 1),
(2, 'frank', 'frank123', 2),
(3, 'oliver', 'oliver123', 1);

CREATE TABLE `student` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `name` varchar(120) NOT NULL,
  `status` varchar(120) NOT NULL,
  `major` varchar(120) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `student` (`id`, `name`, `status`, `major`) VALUES
(1, 'Thomas', 'Full time', 'B'),
(2, 'Frank', 'Part time', 'N'),
(3, 'Oliver', 'Part time', 'D');

CREATE TABLE `subject` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `name` varchar(50) NOT NULL,
  `prerequisite` int(11) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `subject` (`id`, `name`, `prerequisite`) VALUES
(1, 'Subject 1', 0),
(2, 'Subject 2', 1),
(3, 'Subject 3', 2);

CREATE TABLE `student_subject_complete` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `enrolment` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `is_completed` boolean NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `enrolment` (`id`, `student_id`, `subject_id`, `is_completed`) VALUES
(1, 1, 1, 0), (2, 2, 1, 0), (3, 3, 1, 0);