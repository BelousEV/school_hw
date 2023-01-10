package ru.hogwarts.school.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;

import java.util.Optional;

//public interface AvatarRepository extends JpaRepository <Avatar, Long> {

public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {
    Optional<Avatar> findById(Long avatarId); // ищем студента по айди
}