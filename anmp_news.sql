-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2024 at 01:07 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `anmp_news`
--

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE `articles` (
  `id` int(11) NOT NULL,
  `paragraph` varchar(255) NOT NULL,
  `news_id` int(11) NOT NULL,
  `date` text NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`id`, `paragraph`, `news_id`, `date`) VALUES
(1, 'Marvel Studios has officially announced their latest project, \'The Eternals\', set to release next summer. Directed by Chloe Zhao, the film promises to explore a new corner of the Marvel Cinematic Universe. With a star-studded cast including Angelina Jolie', 1, '2024-04-20 11:43:54'),
(2, 'The Eternals, a race of immortal beings with superhuman abilities, have secretly lived on Earth for thousands of years. As they reunite to protect humanity from their ancient enemies, the Deviants, the fate of the universe hangs in the balance. With breat', 1, '2024-04-20 11:43:54'),
(3, 'From the cosmic landscapes of distant planets to the bustling streets of modern-day cities, \'The Eternals\' promises to take audiences on an epic journey unlike any other. With its diverse cast and powerful themes of family, identity, and sacrifice, the fi', 1, '2024-04-20 11:43:54'),
(4, 'Legendary filmmaker Steven Spielberg is set to helm the highly anticipated \'Indiana Jones 5\'. Fans can expect the iconic archaeologist to embark on a new adventure filled with thrills and excitement. With Harrison Ford reprising his role as the legendary ', 2, '2024-04-20 11:43:54'),
(5, 'In \'Indiana Jones 5\', our intrepid adventurer finds himself facing his most dangerous quest yet as he races against time to uncover a long-lost artifact with the power to change the course of history. From the jungles of South America to the deserts of th', 2, '2024-04-20 11:43:54'),
(6, 'Joining Harrison Ford on his epic journey are a cast of talented newcomers and familiar faces, including Phoebe Waller-Bridge, Mads Mikkelsen, and Antonio Banderas. With its thrilling action sequences, pulse-pounding suspense, and unforgettable characters', 2, '2024-04-20 11:43:54'),
(7, 'Renowned director Christopher Nolan has unveiled details about his upcoming film, which promises to be a mind-bending thriller. With a star-studded cast including Leonardo DiCaprio, Cillian Murphy, and Tom Hardy, anticipation is at an all-time high. Nolan', 3, '2024-04-20 11:43:54'),
(8, 'Set in a dystopian future where technology has become indistinguishable from reality, the film follows a group of hackers who discover a way to manipulate people\'s dreams. As they delve deeper into the subconscious, they uncover a conspiracy that threaten', 3, '2024-04-20 11:43:54'),
(9, 'From the towering skyscrapers of a futuristic metropolis to the haunting landscapes of a post-apocalyptic wasteland, Christopher Nolan\'s vision of the future is both breathtaking and terrifying. With its mind-bending plot twists and awe-inspiring visuals,', 3, '2024-04-20 11:43:54'),
(10, 'Animation powerhouse Pixar has surprised fans with the announcement of \'Toy Story 5\'. Buzz, Woody, and the gang return for another heartwarming adventure that is sure to delight audiences of all ages. With its beloved characters, heartfelt storytelling, a', 4, '2024-04-20 11:43:54'),
(11, 'In \'Toy Story 5\', our favorite toys find themselves facing their biggest challenge yet as they embark on a cross-country road trip to reunite with a long-lost friend. Along the way, they encounter new friends, old enemies, and plenty of surprises. From th', 4, '2024-04-20 11:43:54'),
(12, 'Join Buzz, Woody, and the rest of the gang as they embark on an epic adventure that will test their courage, loyalty, and friendship. With its humor, heart, and message of hope, \'Toy Story 5\' is a must-see for fans of all ages. Get ready to laugh, cry, an', 4, '2024-04-20 11:43:54'),
(13, 'After much anticipation, the latest installment in the James Bond franchise, \'No Time to Die\', is finally hitting theaters. Daniel Craig\'s final outing as the iconic spy promises action-packed thrills and a fitting send-off. With its dazzling action seque', 5, '2024-04-20 11:43:54'),
(14, 'In \'No Time to Die\', James Bond finds himself facing his most dangerous adversary yet as he races against time to stop a mysterious villain with a deadly weapon. With the help of old friends and new allies, Bond must uncover the truth behind the conspirac', 5, '2024-04-20 11:43:54');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `description` varchar(500) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`id`, `title`, `user_id`, `description`, `image_url`, `date`) VALUES
(1, 'New Marvel Movie Announced: \'The Eternals\'', 1, 'Marvel Studios has officially announced their latest project, \'The Eternals\', set to release next summer. Directed by Chloe Zhao, the film promises to explore a new corner of the Marvel Cinematic Universe.', 'https://phantom-marca.unidadeditorial.es/ef865faab0a1ef31caaa6cac07db8690/crop/68x0/1311x700/resize/828/f/jpg/assets/multimedia/imagenes/2021/08/20/16294695683527.jpg', '2024-04-20'),
(2, 'Steven Spielberg Returns with \'Indiana Jones 5\'', 1, 'Legendary filmmaker Steven Spielberg is set to helm the highly anticipated \'Indiana Jones 5\'. Fans can expect the iconic archaeologist to embark on a new adventure filled with thrills and excitement.', 'https://www.rollingstone.com/wp-content/uploads/2022/12/plt_dtlr1_uhd_r709f_stills_221123.088814c.jpg?w=1581&h=1054&crop=1', '2024-04-20'),
(3, 'Christopher Nolan\'s Mystery Project Revealed', 2, 'Renowned director Christopher Nolan has unveiled details about his upcoming film, which promises to be a mind-bending thriller. With a star-studded cast and Nolan\'s trademark style, anticipation is at an all-time high.', 'https://miro.medium.com/v2/resize:fit:1400/1*yhd5QHeecAiB5QjtFfuIzg.jpeg', '2024-04-20'),
(4, 'Pixar Announces \'Toy Story 5\'', 2, 'Animation powerhouse Pixar has surprised fans with the announcement of \'Toy Story 5\'. Buzz, Woody, and the gang return for another heartwarming adventure that is sure to delight audiences of all ages.', 'https://i.ytimg.com/vi/08nUTrQ1tKI/maxresdefault.jpg', '2024-04-20'),
(5, 'New James Bond Film, \'No Time to Die\', Hits Theaters', 3, 'After much anticipation, the latest installment in the James Bond franchise, \'No Time to Die\', is finally hitting theaters. Daniel Craig\'s final outing as the iconic spy promises action-packed thrills and a fitting send-off.', 'https://upload.wikimedia.org/wikipedia/id/2/2f/Poster_NTTD.jpg', '2024-04-20');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(10) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `first_name`, `last_name`, `email`, `password`, `image_url`) VALUES
(1, 'gamal066', 'Gamaliel', 'Duta', 'gamaliel@gmail.com', 'gamal123', ''),
(2, 'robert070', 'Robert', 'De Niro', 'robert070@gmail.com', 'robert1234', ''),
(3, 'sasageyo', 'Erwin', 'Smith', 'onearmnoproblem@gmail.com', 'sasageyo', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `news_id` (`news_id`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_users_username` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`);

--
-- Constraints for table `news`
--
ALTER TABLE `news`
  ADD CONSTRAINT `fk_users_username` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
