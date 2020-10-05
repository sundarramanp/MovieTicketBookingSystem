
INSERT INTO `institute`.`user_master` (`id`, `password`, `status`, `create_date`, `create_user`, 
`last_updated_date`, `last_updated_user`, `login_id`, `user_type`, `version_lock`)
 VALUES ('1', '$2a$10$6xFnWnXXfHIP3lgOY/4Cs.knPmD1nG5N.X7etZudElGwRv4oNXtTm', 'ACTIVE', sysdate(), 's', sysdate(), 's', 'SUNDAR', 'ADMIN', '1');
 commit;