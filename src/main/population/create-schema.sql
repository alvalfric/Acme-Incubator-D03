
    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `alferez_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(256),
        `moment` datetime(6),
        `type` varchar(256),
        `url` varchar(4096),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `cvv` varchar(4),
        `brand` varchar(60),
        `expiration_date` varchar(7),
        `holder_name` varchar(60),
        `number` varchar(16),
        `picture` varchar(2048),
        `slogan` varchar(256),
        `url` varchar(2048),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `average_goal` varchar(256),
        `average_reward` varchar(256),
        `deadline` datetime(6),
        `description` varchar(4096),
        `expert_goal` varchar(256),
        `expert_reward` varchar(256),
        `rookie_goal` varchar(256),
        `rookie_reward` varchar(256),
        `title` varchar(256),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customization_parameter` (
       `id` integer not null,
        `version` integer not null,
        `activity_sectors` varchar(4096),
        `spam_threshold` double precision,
        `spam_words_english` varchar(4096),
        `spam_words_spanish` varchar(4096),
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquirie` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(4096),
        `contact_email` varchar(320),
        `creation` datetime(6),
        `deadline` datetime(6),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `title` varchar(256),
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(2048),
        `creation` datetime(6),
        `deadline` datetime(6),
        `header_picture` varchar(2048),
        `related_link1` varchar(2048),
        `related_link2` varchar(2048),
        `title` varchar(256),
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(4096),
        `contact_email` varchar(320),
        `creation` datetime(6),
        `deadline` datetime(6),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `title` varchar(256),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `shout` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(1024),
        `moment` datetime(6),
        `text` varchar(4096),
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(256),
        `description` varchar(4096),
        `email` varchar(320),
        `inventor_name` varchar(256),
        `source_type` varchar(256),
        `stars` integer,
        `title` varchar(256),
        `website` varchar(2048),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(256),
        `description` varchar(4096),
        `email` varchar(320),
        `inventor_name` varchar(256),
        `source_type` varchar(256),
        `stars` integer,
        `title` varchar(256),
        `website` varchar(2048),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXnr284tes3x8hnd3h716tmb3fr on `challenge` (`deadline`);
create index IDXdvftjmbbmrad2oe19yi4uuhyi on `inquirie` (`deadline`);
create index IDXrcpel5hblr62lfjr9gmpk2wgi on `notice` (`deadline`);
create index IDX3ianip0mmnj1316lpeas2yw71 on `overture` (`deadline`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
