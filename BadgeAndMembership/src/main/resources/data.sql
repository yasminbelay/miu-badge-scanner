--------------------------------------------------
-- Inserting roles in role table
--------------------------------------------------
insert into role (id, name) values (1, 'ADMIN');
insert into role (id, name) values (2, 'STUDENT');
insert into role (id, name) values (3, 'FACULTY');
insert into role (id, name) values (4, 'STAFF');
--------------------------------------------------
-- Inserting members
--------------------------------------------------
insert into member (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) values (1, 'Admin', 'Admin', 'admin@miu.edu', '$2a$10$A/jANVjmnu428/cT2Z2Cmu8qAihZxaWnfKZH4ghrGB7y/Ji9tUFGG'); --password
insert into MEMBER_ROLES (MEMBER_ID, ROLE_ID) values (1, 1);
insert into MEMBER (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) values (2, 'Bipul', 'Ranjitkar', 'bipul.test@miu.edu', '$2a$10$A/jANVjmnu428/cT2Z2Cmu8qAihZxaWnfKZH4ghrGB7y/Ji9tUFGG'); --password
insert into MEMBER_ROLES (MEMBER_ID, ROLE_ID) values (2, 2);
insert into MEMBER (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) values (3, 'Pukar', 'Subedi', 'pukar.test@miu.edu', '$2a$10$A/jANVjmnu428/cT2Z2Cmu8qAihZxaWnfKZH4ghrGB7y/Ji9tUFGG'); --password
insert into MEMBER_ROLES (MEMBER_ID, ROLE_ID) values (3, 3);
--------------------------------------------------
-- Inserting badges for members
--------------------------------------------------
insert into BADGE (ID, BADGE_UID, CREATED_DATE, IS_ACTIVE, MODIFIED_DATE, MEMBER) values (1, 'badge1', current_date, true, current_date, 1);
insert into BADGE (ID, BADGE_UID, CREATED_DATE, IS_ACTIVE, MODIFIED_DATE, MEMBER) values (2, 'badge2', current_date, true, current_date, 2);
insert into BADGE (ID, BADGE_UID, CREATED_DATE, IS_ACTIVE, MODIFIED_DATE, MEMBER) values (3, 'badge3', current_date, true, current_date, 3);
insert into BADGE (ID, BADGE_UID, CREATED_DATE, IS_ACTIVE, MODIFIED_DATE, MEMBER) values (4, 'badge4', current_date, false, current_date, 2);
insert into BADGE (ID, BADGE_UID, CREATED_DATE, IS_ACTIVE, MODIFIED_DATE, MEMBER) values (5, 'badge5', current_date, false, current_date, 2);

--------------------------------------------------
-- Inserting plans
--------------------------------------------------
-----plan 1--------
    insert into PLAN (ID, NAME, DESCRIPTION) values (1, 'Meal Plan', 'All meal plan');
    --assigning allowed roles
    insert into PLAN_ALLOWED_ROLES (PLAN_ID, ALLOWED_ROLE_ID) values (1, 2);
    insert into PLAN_ALLOWED_ROLES (PLAN_ID, ALLOWED_ROLE_ID) values (1, 3);
    insert into PLAN_ALLOWED_ROLES (PLAN_ID, ALLOWED_ROLE_ID) values (1, 4);
-----plan 2--------
    insert into PLAN (ID, NAME, DESCRIPTION) values (2, 'Gym Plan', 'Gym plan');
    --assigning allowed roles
    insert into PLAN_ALLOWED_ROLES (PLAN_ID, ALLOWED_ROLE_ID) values (2, 2);
    insert into PLAN_ALLOWED_ROLES (PLAN_ID, ALLOWED_ROLE_ID) values (2, 3);
    insert into PLAN_ALLOWED_ROLES (PLAN_ID, ALLOWED_ROLE_ID) values (2, 4);

--------------------------------------------------
-- Inserting locations
--------------------------------------------------
insert into LOCATION (ID, NAME, DESCRIPTION, CAPACITY, LOCATION_TYPE, PLAN_ID) values (1, 'Annapurna Hall', 'Annapurna Hall Argiro', 3, 'DINING_HALL', 1);
insert into LOCATION (ID, NAME, DESCRIPTION, CAPACITY, LOCATION_TYPE, PLAN_ID) values (2, 'Festival Hall', 'Festival Hall Argiro', 20, 'DINING_HALL', 1);
insert into LOCATION (ID, NAME, DESCRIPTION, CAPACITY, LOCATION_TYPE, PLAN_ID) values (3, 'Recreational Center', 'Recreational Center', 120, 'GYMNASIUM', 2);
insert into LOCATION (ID, NAME, DESCRIPTION, CAPACITY, LOCATION_TYPE, PLAN_ID) values (4, 'Male Golden Dome', 'Male Golden Dome', 120, 'MEDITATION_HALL', 2);
--------------------------------------------------
-- Inserting timeslots for locations
--------------------------------------------------
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (1, '11:00:00', '13:30:00', 1);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (1, '18:00:00', '23:30:00', 1);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (2, '8:30:00', '10:00:00', 1);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (2, '12:00:00', '13:30:00', 1);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (2, '15:00:00', '19:30:00', 1);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (3, '8:30:00', '10:00:00', 1);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (3, '11:00:00', '13:30:00', 1);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (3, '15:00:00', '19:30:00', 1);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (1, '11:00:00', '13:30:00', 2);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (1, '18:00:00', '19:30:00', 2);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (2, '9:00:00', '20:00:00', 3);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (3, '9:00:00', '20:00:00', 3);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (4, '9:00:00', '20:00:00', 3);
insert into TIMESLOT (DAY_OF_WEEK, START_TIME, END_TIME, LOCATION_ID) values (4, '9:00:00', '20:00:00', 4);

--------------------------------------------------
-- Inserting Memberships
--------------------------------------------------
insert into MEMBERSHIP (START_DATE, END_DATE, PLAN_ID, MEMBER_ID, MEMBERSHIP_TYPE, NUMBER_OF_ALLOWANCE_LIMIT, LIMIT_RESET_TIME_PERIOD, IS_SINGLE_ENTRY_PER_TIME_SLOT)
    values ('2022-08-01', '2023-08-01', 1, 2, 'LIMITED', 20, 'WEEKLY', 1);
insert into MEMBERSHIP (START_DATE, END_DATE, PLAN_ID, MEMBER_ID, MEMBERSHIP_TYPE, NUMBER_OF_ALLOWANCE_LIMIT, LIMIT_RESET_TIME_PERIOD, IS_SINGLE_ENTRY_PER_TIME_SLOT)
    values ('2022-08-01', '2023-08-01', 2, 2, 'UNLIMITED', 0, 'NA', 0);
insert into MEMBERSHIP (START_DATE, END_DATE, PLAN_ID, MEMBER_ID, MEMBERSHIP_TYPE, NUMBER_OF_ALLOWANCE_LIMIT, LIMIT_RESET_TIME_PERIOD, IS_SINGLE_ENTRY_PER_TIME_SLOT)
    values ('2022-08-01', '2023-08-01', 1, 3, 'LIMITED', 30, 'MONTHLY', 0);
insert into MEMBERSHIP (START_DATE, END_DATE, PLAN_ID, MEMBER_ID, MEMBERSHIP_TYPE, NUMBER_OF_ALLOWANCE_LIMIT, LIMIT_RESET_TIME_PERIOD, IS_SINGLE_ENTRY_PER_TIME_SLOT)
values ('2022-08-01', '2023-08-01', 1, 3, 'CHECKER', 0, 'NA', 0);


