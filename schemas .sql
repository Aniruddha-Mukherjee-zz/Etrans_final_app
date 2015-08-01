CREATE SCHEMA Pr_InPlant

-- changed most character variables to "text"

CREATE TABLE Pr_InPlant.tbl_plant(
i_plant_id          numeric(32,0) NOT NULL,           --primary key
t_plant_name        text,
t_plant_owner       text,

    /*Further Details */
CONSTRAINT pk_plant_plant_id PRIMARY KEY (i_plant_id)
);

CREATE TABLE  Pr_InPlant.tbl_live_path (
    i_plant_id  numeric(32,0) NOT NULL,
    i_path_id   numeric(32,0) NOT NULL,
    t_curr_path text,
    t_next      text,
CONSTRAINT pk_live_path_id PRIMARY KEY (i_path_id),
CONSTRAINT fk_live_path_plant_id FOREIGN KEY (i_plant_id) REFERENCES  Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);

CREATE TABLE Pr_InPlant.tbl_card(
i_plant_id          numeric(32,0) NOT NULL,
c_card_id           character varying(32) NOT NULL,	--primary key

CONSTRAINT pk_card_card_id PRIMARY KEY (c_card_id),
CONSTRAINT fk_card_plant_id FOREIGN KEY  (i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);

CREATE  TABLE Pr_InPlant.tbl_transporter(
i_plant_id              numeric(32,0) NOT NULL,
i_transporter_id        numeric(50,0) NOT NULL,    --primary key
t_transporter_name      text,
t_transporter_mobile_no text,
/*s_drivers_under_him character varying(50),
s_vehicles_under_him     character varying(50),*/
t_transporter_addr      text,
t_transporter_city      text,
t_transporter_state     text,
i_transporter_pin       text,
t_transporter_email_id  text,
CONSTRAINT pk_transporter_transporter_id PRIMARY KEY (i_transporter_id),
CONSTRAINT fk_transporter_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);

CREATE TABLE Pr_InPlant.tbl_driver (
i_plant_id          numeric(32,0) NOT NULL,
i_driver_id         numeric(32,0) NOT NULL,           --primary key
i_transporter_id    numeric(32,0) NOT NULL,
t_driver_name       text,
t_driver_addr1      text,
t_driver_addr2      text,
t_driver_city       text,
t_state_id          text,
i_driver_pin        text,
t_country_id        text,
t_driver_email_id   text,
t_licence_no        text NOT NULL,
b_is_active         boolean,  				-- changed to boolean
t_created_by        text  default 'SYS',
dt_created          timestamp  default CURRENT_timestamp,
t_modified_by       text,
dt_modified         timestamp,
b_is_deleted        boolean,			-- changed to boolean
CONSTRAINT pk_driver_driver_id PRIMARY KEY (i_driver_id),
CONSTRAINT fk_driver_transporter_id FOREIGN KEY(i_transporter_id) REFERENCES Pr_InPlant.tbl_transporter(i_transporter_id) MATCH FULL,
CONSTRAINT fk_driver_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id)  MATCH FULL
);


CREATE TABLE pr_inplant.tbl_data
(
  epos_data text NOT NULL,
  seen boolean NOT NULL,
  i_data_id numeric NOT NULL,
  CONSTRAINT pk_data_data_id PRIMARY KEY (i_data_id)
);

CREATE  TABLE Pr_InPlant.tbl_vehicle(
i_plant_id          numeric(32,0) NOT NULL,
t_vehicle_id        text NOT NULL,  --registration no is the primary key
t_chasis_no         text,
t_vehicle_type      text,
i_load_capacity     numeric(10,0),
t_owner             text,
t_National_Permit   text,
dt_License_Expiry_Date   timestamp,
CONSTRAINT pk_vehicle_vehicle_id PRIMARY KEY(t_vehicle_id),
CONSTRAINT fk_vehicle_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);

CREATE  TABLE Pr_InPlant.tbl_users(
i_plant_id          numeric(32,0) NOT NULL,
s_username        text NOT NULL,    --primary key
s_password        text,
s_password_md5    text NOT NULL,
CONSTRAINT pk_users_username PRIMARY KEY(s_username),
CONSTRAINT fk_users_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);




CREATE TABLE Pr_InPlant.tbl_do(
i_plant_id               numeric(32,0) NOT NULL,
i_do_id                  numeric(30,0) NOT NULL,    --primary key
dt_issue_date            timestamp,
t_consignee              text,
t_consigner              text,
t_delivery_address       text,
CONSTRAINT pk_do_do_id PRIMARY KEY (i_do_id),
CONSTRAINT fk_do_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);

CREATE TABLE Pr_InPlant.tbl_epos(
i_plant_id              numeric(32,0) NOT NULL,
i_terminal_id           numeric(32,0) NOT NULL,
i_machine_id            numeric(30,0) NOT NULL,    --primary key
i_gate_no               numeric(50,0),
t_gateway_name          text,
t_location              text,
b_bothways              boolean NOT NULL,
i_time_in_between       numeric(30,0),
CONSTRAINT pk_epos_machine_id PRIMARY KEY (i_machine_id),
CONSTRAINT fk_epos_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);


CREATE TABLE Pr_InPlant.tbl_process(
i_plant_id              numeric(32,0) NOT NULL,
i_process_id            numeric (30,0) NOT NULL,    --primary key
t_process_type          text,
CONSTRAINT pk_process_process_id PRIMARY KEY (i_process_id),
CONSTRAINT fk_process_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);

CREATE TABLE Pr_InPlant.tbl_mapping(
i_plant_id              numeric(32,0) NOT NULL,
i_trip_id               numeric(30,0) NOT NULL,   --primary key
c_card_id               character varying(32) NOT NULL,
i_process_id            numeric (30,0),
t_vehicle_id            text NOT NULL,
i_driver_id             numeric (30,0),
i_transporter_id        numeric (30,0),
i_do_id                 numeric (30,0),
dt_created              timestamp  default CURRENT_timestamp,
b_is_active             boolean NOT NULL,
i_path_id               numeric(32,0) NOT NULL,
CONSTRAINT pk_mapping_trip    PRIMARY KEY (i_trip_id),
CONSTRAINT fk_mapping_vehicle_id FOREIGN KEY(t_vehicle_id) REFERENCES Pr_InPlant.tbl_vehicle(t_vehicle_id) MATCH FULL,
CONSTRAINT fk_mapping_driver_id FOREIGN KEY(i_driver_id) REFERENCES Pr_InPlant.tbl_driver(i_driver_id) MATCH FULL,
CONSTRAINT fk_mapping_transporter_id FOREIGN KEY(i_transporter_id) REFERENCES Pr_InPlant.tbl_transporter(i_transporter_id) MATCH FULL,
CONSTRAINT fk_mapping_do_id FOREIGN KEY(i_do_id) REFERENCES Pr_InPlant.tbl_do(i_do_id) MATCH FULL,
CONSTRAINT fk_mapping_process_id FOREIGN KEY(i_process_id) REFERENCES Pr_InPlant.tbl_process(i_process_id) MATCH FULL,
CONSTRAINT fk_mapping_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL,
CONSTRAINT fk_mapping_card_id FOREIGN KEY(c_card_id) REFERENCES Pr_InPlant.tbl_card(c_card_id) MATCH FULL,
CONSTRAINT fk_mapping_path_id FOREIGN KEY (i_path_id) REFERENCES Pr_InPlant.tbl_live_path(i_path_id) MATCH FULL
);

-- added primary key
CREATE TABLE Pr_InPlant.tbl_vehicle_flight(
i_vfid		               numeric(32,0) NOT NULL,
dt_time                  timestamp,
i_machine_id             numeric(50,0)  NOT NULL,
c_card_id                character varying(32) NOT NULL,
i_trip_id                numeric(50,0)  NOT NULL,
t_model_no               text,
t_data_status            text,
t_event                  text,
CONSTRAINT pk_vehicle_flight_vfid PRIMARY KEY (i_vfid),
CONSTRAINT fk_vehicle_flight_m_id FOREIGN KEY (i_machine_id) REFERENCES Pr_InPlant.tbl_epos(i_machine_id) MATCH FULL,
CONSTRAINT fk_vehicle_flight_trip_id FOREIGN KEY (i_trip_id) REFERENCES Pr_InPlant.tbl_mapping(i_trip_id) MATCH FULL,
CONSTRAINT fk_vehicle_card_id FOREIGN KEY(c_card_id) REFERENCES Pr_InPlant.tbl_card(c_card_id) MATCH FULL
);

CREATE TABLE Pr_InPlant.tbl_exceptions(
i_exception_id            numeric(32,0) NOT NULL,
dt_created               timestamp  default CURRENT_timestamp,    --primary key
i_plant_id               numeric(32,0) NOT NULL,
i_trip_id                numeric(32,0) NOT NULL,
i_machine_id             numeric(30,0) NOT NULL,
dt_expected_time         timestamp,
dt_arrival_time           timestamp,
b_is_checked             boolean NOT NULL,
CONSTRAINT pk_exceptions_exception_id   PRIMARY KEY(i_exception_id),
CONSTRAINT fk_exceptions_trip_id FOREIGN KEY(i_trip_id) REFERENCES Pr_InPlant.tbl_mapping(i_trip_id) MATCH FULL,
CONSTRAINT fk_exceptions_m_id FOREIGN KEY (i_machine_id) REFERENCES Pr_InPlant.tbl_epos(i_machine_id) MATCH FULL,
CONSTRAINT fk_exceptions_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);

CREATE TABLE Pr_InPlant.tbl_links(
i_lid 					                   numeric(32,0) NOT NULL,
i_plant_id                         numeric(32,0) NOT NULL,
i_to                               numeric(30,0),
i_from                             numeric(30,0),
n_timediff_in_min               numeric,

CONSTRAINT pk_links_lid   PRIMARY KEY(i_lid),
CONSTRAINT fk_links_to FOREIGN KEY (i_to) REFERENCES Pr_InPlant.tbl_epos(i_machine_id) MATCH FULL,
CONSTRAINT fk_links_from FOREIGN KEY (i_from) REFERENCES Pr_InPlant.tbl_epos(i_machine_id) MATCH FULL,
CONSTRAINT fk_links_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);

CREATE TABLE Pr_InPlant.tbl_paths(
i_plant_id              numeric(32,0) NOT NULL,
i_arr_path              numeric(30,0)[], --array of epos's    --primary key
t_name                  text,
CONSTRAINT pk_paths_path PRIMARY KEY (i_arr_path),
CONSTRAINT fk_paths_plant_id FOREIGN KEY(i_plant_id) REFERENCES Pr_InPlant.tbl_plant(i_plant_id) MATCH FULL
);
