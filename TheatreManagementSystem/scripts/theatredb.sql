--
-- PostgreSQL database dump
--

-- Dumped from database version 14.6
-- Dumped by pg_dump version 14.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: actors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actors (
    actor_id integer NOT NULL,
    name character varying(64) NOT NULL,
    surname character varying(64) NOT NULL,
    photo_url character varying(1024) NOT NULL
);


ALTER TABLE public.actors OWNER TO postgres;

--
-- Name: actors_actor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.actors_actor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.actors_actor_id_seq OWNER TO postgres;

--
-- Name: actors_actor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.actors_actor_id_seq OWNED BY public.actors.actor_id;


--
-- Name: auth_white_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auth_white_list (
    id integer NOT NULL,
    email character varying(256) NOT NULL,
    role character varying(256) NOT NULL
);


ALTER TABLE public.auth_white_list OWNER TO postgres;

--
-- Name: auth_white_list_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auth_white_list_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_white_list_id_seq OWNER TO postgres;

--
-- Name: auth_white_list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auth_white_list_id_seq OWNED BY public.auth_white_list.id;


--
-- Name: cast; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."cast" (
    performance_id integer NOT NULL,
    actor_id integer NOT NULL,
    role character varying(256) NOT NULL
);


ALTER TABLE public."cast" OWNER TO postgres;

--
-- Name: halls; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.halls (
    hall_name character varying(256) NOT NULL,
    background_path character varying(256) NOT NULL,
    seat_scale double precision NOT NULL
);


ALTER TABLE public.halls OWNER TO postgres;

--
-- Name: performance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.performance (
    id integer NOT NULL,
    title character varying(256) NOT NULL,
    description character varying(1024) NOT NULL,
    image_url character varying(1024) NOT NULL,
    length integer NOT NULL
);


ALTER TABLE public.performance OWNER TO postgres;

--
-- Name: performance_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_id_seq OWNER TO postgres;

--
-- Name: performance_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_id_seq OWNED BY public.performance.id;


--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    id integer NOT NULL,
    reservation_date date NOT NULL,
    reservation_time time without time zone NOT NULL,
    client_name character varying(128) NOT NULL,
    client_email character varying(128) NOT NULL,
    phone_number character varying(128),
    id_seance integer NOT NULL,
    reservation_ip_address character varying(128) NOT NULL,
    reservation_auth_mode character varying(128) NOT NULL,
    total_price double precision NOT NULL
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_id_seq OWNER TO postgres;

--
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;


--
-- Name: reservedseats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservedseats (
    id_reservation integer NOT NULL,
    seat_id integer NOT NULL
);


ALTER TABLE public.reservedseats OWNER TO postgres;

--
-- Name: seances; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seances (
    id integer NOT NULL,
    hall_id character varying(256) NOT NULL,
    performance_id integer NOT NULL,
    seance_date date NOT NULL,
    seance_time time without time zone NOT NULL,
    price double precision NOT NULL
);


ALTER TABLE public.seances OWNER TO postgres;

--
-- Name: seances_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seances_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seances_id_seq OWNER TO postgres;

--
-- Name: seances_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.seances_id_seq OWNED BY public.seances.id;


--
-- Name: seats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seats (
    seat_id integer NOT NULL,
    hall_name character varying(256) NOT NULL,
    seat_name character varying(256) NOT NULL,
    pos_x real NOT NULL,
    pos_y real NOT NULL
);


ALTER TABLE public.seats OWNER TO postgres;

--
-- Name: seats_seat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seats_seat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seats_seat_id_seq OWNER TO postgres;

--
-- Name: seats_seat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.seats_seat_id_seq OWNED BY public.seats.seat_id;


--
-- Name: actors actor_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors ALTER COLUMN actor_id SET DEFAULT nextval('public.actors_actor_id_seq'::regclass);


--
-- Name: auth_white_list id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auth_white_list ALTER COLUMN id SET DEFAULT nextval('public.auth_white_list_id_seq'::regclass);


--
-- Name: performance id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance ALTER COLUMN id SET DEFAULT nextval('public.performance_id_seq'::regclass);


--
-- Name: reservation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- Name: seances id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seances ALTER COLUMN id SET DEFAULT nextval('public.seances_id_seq'::regclass);


--
-- Name: seats seat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seats ALTER COLUMN seat_id SET DEFAULT nextval('public.seats_seat_id_seq'::regclass);


--
-- Data for Name: actors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actors (actor_id, name, surname, photo_url) FROM stdin;
\.


--
-- Data for Name: auth_white_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auth_white_list (id, email, role) FROM stdin;
1	zlotymaciej@gmail.com	admin
2	miswistowski@gmail.com	admin
3	rzemieniuk12@gmail.com	actor
\.


--
-- Data for Name: cast; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."cast" (performance_id, actor_id, role) FROM stdin;
\.


--
-- Data for Name: halls; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.halls (hall_name, background_path, seat_scale) FROM stdin;
Mała Sala	C:\\Users\\user\\Desktop\\hall1.png	1
Sala Numer 1	C:\\Users\\user\\Desktop\\hall1.png	1
Wielka Sala	C:\\Users\\user\\Desktop\\hall1.png	1
\.


--
-- Data for Name: performance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.performance (id, title, description, image_url, length) FROM stdin;
1	Jaś i Małgosia	Jaś i Małgosia to dwoje dzieci drwala, które wraz z ojcem mieszkały w chatce na skraju lasu. Ich ojciec był wdowcem, który ożenił się powtórnie po śmierci żony. Nowa macocha nienawidziła jego dzieci. Pewnego dnia w kraju nastał wielki głód. Macocha zaczęła wówczas usilnie namawiać męża, by porzucił dzieci w lesie, gdyż inaczej wszystkich czeka śmierć głodowa. Drwal początkowo odmawiał, ale w końcu uległ namowom żony. Zgodnie z jej planem mieli wszyscy razem udać się po chrust do lasu, a kiedy zacznie się ściemniać drwal i macocha mieli szybko skryć się i zostawić dzieci. 	https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/H%C3%A4nsel_und_Gretel2.jpg/636px-H%C3%A4nsel_und_Gretel2.jpg	90
2	Upiór w operze	musical stworzony w 1986 przez Andrew Lloyda Webbera z liberettem Charlesa Harta i fragmentami liberetta Richarda Stilgoe, na podstawie powieści Upiór w operze Gastona Leroux z 1911 roku. Fabuła musicalu dotyczy losów młodej sopranistki Christine Daaé, która staje się obsesją tajemniczego, zdeformowanego geniusza muzycznego, znanego jako Upiór z opery.	https://imaging.broadway.com/images/regular-43/w735/114595-22.jpg	123
3	Upiór w operze2	musical stworzony w 1986 przez Andrew Lloyda Webbera z liberettem Charlesa Harta i fragmentami liberetta Richarda Stilgoe, na podstawie powieści Upiór w operze Gastona Leroux z 1911 roku. Fabuła musicalu dotyczy losów młodej sopranistki Christine Daaé, która staje się obsesją tajemniczego, zdeformowanego geniusza muzycznego, znanego jako Upiór z opery.	https://imaging.broadway.com/images/regular-43/w735/114595-22.jpg	123
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id, reservation_date, reservation_time, client_name, client_email, phone_number, id_seance, reservation_ip_address, reservation_auth_mode, total_price) FROM stdin;
7	2023-01-09	21:13:55	sdfgfdsfd	miswistowski@gmail.com	456123098	19	IP	Form	270
8	2023-01-09	21:18:39	Świstowski	miswistowski@gmail.com	\N	19	IP	oauth	225
9	2023-01-09	21:19:02	sdfgh	miswistowski@gmail.com	123654789	19	IP	Form	225
\.


--
-- Data for Name: reservedseats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservedseats (id_reservation, seat_id) FROM stdin;
7	323
7	333
7	343
7	403
7	413
7	423
8	112
8	122
8	193
8	203
8	213
9	118
9	128
9	199
9	209
9	219
\.


--
-- Data for Name: seances; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.seances (id, hall_id, performance_id, seance_date, seance_time, price) FROM stdin;
1	Wielka Sala	3	2023-01-09	20:00:00	43
2	Wielka Sala	3	2023-01-10	16:00:00	46
3	Wielka Sala	3	2023-01-11	20:15:00	40
4	Sala Numer 1	3	2023-01-09	20:30:00	21
5	Mała Sala	3	2023-01-10	21:15:00	28
6	Wielka Sala	3	2023-01-11	19:45:00	38
7	Mała Sala	3	2023-01-09	16:30:00	40
8	Mała Sala	3	2023-01-10	22:00:00	31
9	Sala Numer 1	3	2023-01-11	22:45:00	27
10	Sala Numer 1	1	2023-01-09	21:15:00	33
11	Mała Sala	1	2023-01-10	16:15:00	44
12	Mała Sala	1	2023-01-11	19:00:00	38
13	Sala Numer 1	1	2023-01-09	16:00:00	43
14	Mała Sala	1	2023-01-10	18:45:00	20
15	Wielka Sala	1	2023-01-11	22:00:00	32
16	Sala Numer 1	2	2023-01-09	18:00:00	41
17	Sala Numer 1	2	2023-01-10	20:45:00	28
18	Mała Sala	2	2023-01-11	19:00:00	31
19	Wielka Sala	1	2023-01-09	16:30:00	45
20	Mała Sala	1	2023-01-10	20:30:00	26
21	Mała Sala	1	2023-01-11	22:15:00	42
\.


--
-- Data for Name: seats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.seats (seat_id, hall_name, seat_name, pos_x, pos_y) FROM stdin;
1	Mała Sala	Miejsce 1	-0.065356866	-0.522855
2	Mała Sala	Miejsce 1	-0.065356866	-3.7272406
3	Mała Sala	Miejsce 1	-12.684114	7.382617
4	Mała Sala	Miejsce 1	-14.564391	4.78788
5	Mała Sala	Miejsce 1	14.649837	0.86966085
6	Mała Sala	Miejsce 1	16.368599	-1.8347716
7	Mała Sala	Miejsce 1	-12.17071	-7.637429
8	Mała Sala	Miejsce 1	-13.196173	-10.673303
9	Mała Sala	Miejsce 1	11.138491	-11.317796
10	Mała Sala	Miejsce 1	12.28006	-14.311944
11	Mała Sala	Miejsce 2	3.6118069	-0.522855
12	Mała Sala	Miejsce 2	3.6118069	-3.7272406
13	Mała Sala	Miejsce 2	-9.70655	5.2249227
14	Mała Sala	Miejsce 2	-11.586824	2.6301868
15	Mała Sala	Miejsce 2	17.753283	2.8420088
16	Mała Sala	Miejsce 2	19.472046	0.13757849
17	Mała Sala	Miejsce 2	-8.686923	-8.814191
18	Mała Sala	Miejsce 2	-9.712384	-11.850062
19	Mała Sala	Miejsce 2	14.574395	-10.007801
20	Mała Sala	Miejsce 2	15.715965	-13.001946
21	Mała Sala	Miejsce 3	7.1313787	-0.522855
22	Mała Sala	Miejsce 3	7.1313787	-3.7272406
23	Mała Sala	Miejsce 3	-6.856594	3.1597018
24	Mała Sala	Miejsce 3	-8.73687	0.56496626
25	Mała Sala	Miejsce 3	20.723724	4.7298284
26	Mała Sala	Miejsce 3	22.442486	2.0253978
27	Mała Sala	Miejsce 3	-5.3524404	-9.940517
28	Mała Sala	Miejsce 3	-6.3779025	-12.97639
29	Mała Sala	Miejsce 3	17.863047	-8.753944
30	Mała Sala	Miejsce 3	19.004618	-11.748092
31	Mała Sala	Miejsce 4	10.834807	-0.522855
32	Mała Sala	Miejsce 4	10.834807	-3.7272406
33	Mała Sala	Miejsce 4	-3.8577607	0.9865964
34	Mała Sala	Miejsce 4	-5.7380366	-1.6081398
35	Mała Sala	Miejsce 4	23.849337	6.716266
36	Mała Sala	Miejsce 4	25.5681	4.011835
37	Mała Sala	Miejsce 4	-1.8437693	-11.125683
38	Mała Sala	Miejsce 4	-2.8692312	-14.161555
39	Mała Sala	Miejsce 4	21.323494	-7.4345903
40	Mała Sala	Miejsce 4	22.465065	-10.428736
41	Sala Numer 1	A1	0	-5.9002714
42	Sala Numer 1	A1	10.249831	-5.9002714
43	Sala Numer 1	A1	10.249831	0
44	Sala Numer 1	A1	0	0
45	Sala Numer 1	A10	-4.5017796	1.4307024
46	Sala Numer 1	A10	-4.5017796	-4.469569
47	Sala Numer 1	A10	5.7480516	-4.469569
48	Sala Numer 1	A10	5.7480516	1.4307022
49	Sala Numer 1	A11	-5.9694824	1.4307024
50	Sala Numer 1	A11	-5.9694824	-4.469569
51	Sala Numer 1	A11	4.280349	-4.469569
52	Sala Numer 1	A11	4.280349	1.4307022
53	Sala Numer 1	A12	-7.486521	1.4307024
54	Sala Numer 1	A12	-7.486521	-4.469569
55	Sala Numer 1	A12	2.7633104	-4.469569
56	Sala Numer 1	A12	2.7633104	1.4307022
57	Sala Numer 1	A13	0	-2.9771981
58	Sala Numer 1	A13	10.249831	-2.9771981
59	Sala Numer 1	A13	10.249831	2.9230733
60	Sala Numer 1	A14	-1.5170381	2.9230733
61	Sala Numer 1	A14	-1.5170381	-2.9771981
62	Sala Numer 1	A14	8.732793	-2.9771981
63	Sala Numer 1	A14	8.732793	2.9230733
64	Sala Numer 1	A15	-2.9847412	2.9230733
65	Sala Numer 1	A15	-2.9847412	-2.9771981
66	Sala Numer 1	A15	7.26509	-2.9771981
67	Sala Numer 1	A15	7.26509	2.9230733
68	Sala Numer 1	A16	-4.5017796	2.9230733
69	Sala Numer 1	A16	-4.5017796	-2.9771981
70	Sala Numer 1	A16	5.7480516	-2.9771981
71	Sala Numer 1	A16	5.7480516	2.9230733
72	Sala Numer 1	A17	-5.9694824	2.9230733
73	Sala Numer 1	A17	-5.9694824	-2.9771981
74	Sala Numer 1	A17	4.280349	-2.9771981
75	Sala Numer 1	A17	4.280349	2.9230733
76	Sala Numer 1	A18	-7.486521	2.9230733
77	Sala Numer 1	A18	-7.486521	-2.9771981
78	Sala Numer 1	A18	2.7633104	-2.9771981
79	Sala Numer 1	A2	-1.5170381	0
80	Sala Numer 1	A2	-1.5170381	-5.9002714
81	Sala Numer 1	A2	8.732793	-5.9002714
82	Sala Numer 1	A2	8.732793	0
83	Sala Numer 1	A3	-2.9847412	0
84	Sala Numer 1	A3	-2.9847412	-5.9002714
85	Sala Numer 1	A3	7.26509	-5.9002714
86	Sala Numer 1	A3	7.26509	0
87	Sala Numer 1	A4	-4.5017796	0
88	Sala Numer 1	A4	-4.5017796	-5.9002714
89	Sala Numer 1	A4	5.7480516	-5.9002714
90	Sala Numer 1	A4	5.7480516	0
91	Sala Numer 1	A5	-5.9694824	0
92	Sala Numer 1	A5	-5.9694824	-5.9002714
93	Sala Numer 1	A5	4.280349	-5.9002714
94	Sala Numer 1	A5	4.280349	0
95	Sala Numer 1	A6	-7.486521	0
96	Sala Numer 1	A6	-7.486521	-5.9002714
97	Sala Numer 1	A6	2.7633104	-5.9002714
98	Sala Numer 1	A6	2.7633104	0
99	Sala Numer 1	A7	0	1.4307024
100	Sala Numer 1	A7	0	-4.469569
101	Sala Numer 1	A7	10.249831	-4.469569
102	Sala Numer 1	A7	10.249831	1.4307022
103	Sala Numer 1	A8	-1.5170381	1.4307024
104	Sala Numer 1	A8	-1.5170381	-4.469569
105	Sala Numer 1	A8	8.732793	-4.469569
106	Sala Numer 1	A8	8.732793	1.4307022
107	Sala Numer 1	A9	-2.9847412	1.4307024
108	Sala Numer 1	A9	-2.9847412	-4.469569
109	Sala Numer 1	A9	7.26509	-4.469569
110	Sala Numer 1	A9	7.26509	1.4307022
111	Wielka Sala	A1	0	-15.920931
112	Wielka Sala	A1	26.878922	-15.920931
113	Wielka Sala	A1	26.878922	-0.10980034
114	Wielka Sala	A1	0	14.794701
115	Wielka Sala	A1	26.878922	14.6849
116	Wielka Sala	A1	-27.217731	-15.8111315
117	Wielka Sala	A1	-27.217731	-0.4517479
118	Wielka Sala	A1	53.64491	-15.8111315
119	Wielka Sala	A1	53.64491	-0.4517479
120	Wielka Sala	A2	2.947672	0
121	Wielka Sala	A2	2.947672	-15.920931
122	Wielka Sala	A2	29.826595	-15.920931
123	Wielka Sala	A2	29.826595	-0.10980034
124	Wielka Sala	A2	2.947672	14.794701
125	Wielka Sala	A2	29.826595	14.6849
126	Wielka Sala	A2	-24.27006	-15.8111315
127	Wielka Sala	A2	-24.27006	-0.4517479
128	Wielka Sala	A2	56.592583	-15.8111315
129	Wielka Sala	A2	56.592583	-0.4517479
130	Wielka Sala	A3	6.0680504	0
131	Wielka Sala	A3	6.0680504	-15.920931
132	Wielka Sala	A3	32.94697	-15.920931
133	Wielka Sala	A3	32.94697	-0.10980034
134	Wielka Sala	A3	6.0680504	14.794701
135	Wielka Sala	A3	32.94697	14.6849
136	Wielka Sala	A3	-21.149681	-15.8111315
137	Wielka Sala	A3	-21.149681	-0.4517479
138	Wielka Sala	A3	59.71296	-15.8111315
139	Wielka Sala	A3	59.71296	-0.4517479
140	Wielka Sala	A4	9.015722	-15.920931
141	Wielka Sala	A4	35.89464	-15.920931
142	Wielka Sala	A4	35.89464	-0.10980034
143	Wielka Sala	A4	9.015722	0
144	Wielka Sala	A4	9.015722	14.794701
145	Wielka Sala	A4	35.89464	14.6849
146	Wielka Sala	A4	-18.20201	-15.8111315
147	Wielka Sala	A4	-18.20201	-0.4517479
148	Wielka Sala	A4	62.66063	-15.8111315
149	Wielka Sala	A4	62.66063	-0.4517479
150	Wielka Sala	A5	12.251421	-15.920931
151	Wielka Sala	A5	39.130344	-15.920931
152	Wielka Sala	A5	39.130344	-0.10980034
153	Wielka Sala	A5	12.251421	14.794701
154	Wielka Sala	A5	12.251421	0
155	Wielka Sala	A5	39.130344	14.6849
156	Wielka Sala	A5	-14.9663105	-15.8111315
157	Wielka Sala	A5	-14.9663105	-0.4517479
158	Wielka Sala	A5	65.89633	-15.8111315
159	Wielka Sala	A5	65.89633	-0.4517479
160	Wielka Sala	A6	15.199093	-15.920931
161	Wielka Sala	A6	42.078014	-15.920931
162	Wielka Sala	A6	42.078014	-0.10980034
163	Wielka Sala	A6	15.199093	14.794701
164	Wielka Sala	A6	42.078014	14.6849
165	Wielka Sala	A6	15.199093	0
166	Wielka Sala	A6	-12.018639	-15.8111315
167	Wielka Sala	A6	-12.018639	-0.4517479
168	Wielka Sala	A6	68.844	-15.8111315
169	Wielka Sala	A6	68.844	-0.4517479
170	Wielka Sala	A7	18.319471	-15.920931
171	Wielka Sala	A7	45.19839	-15.920931
172	Wielka Sala	A7	45.19839	-0.10980034
173	Wielka Sala	A7	18.319471	14.794701
174	Wielka Sala	A7	45.19839	14.6849
175	Wielka Sala	A7	-8.89826	-15.8111315
176	Wielka Sala	A7	18.319471	0
177	Wielka Sala	A7	-8.89826	-0.4517479
178	Wielka Sala	A7	71.96438	-15.8111315
179	Wielka Sala	A7	71.96438	-0.4517479
180	Wielka Sala	A8	21.267143	-15.920931
181	Wielka Sala	A8	48.146065	-15.920931
182	Wielka Sala	A8	48.146065	-0.10980034
183	Wielka Sala	A8	21.267143	14.794701
184	Wielka Sala	A8	48.146065	14.6849
185	Wielka Sala	A8	-5.950588	-15.8111315
186	Wielka Sala	A8	-5.950588	-0.4517479
187	Wielka Sala	A8	21.267143	0
188	Wielka Sala	A8	74.91205	-15.8111315
189	Wielka Sala	A8	74.91205	-0.4517479
190	Wielka Sala	A1	0	0
191	Wielka Sala	B1	0	-2.8103445
192	Wielka Sala	B1	0	-18.731276
193	Wielka Sala	B1	26.878922	-18.731276
194	Wielka Sala	B1	26.878922	-2.920145
195	Wielka Sala	B1	0	11.984356
196	Wielka Sala	B1	26.878922	11.874556
197	Wielka Sala	B1	-27.217731	-18.621475
198	Wielka Sala	B1	-27.217731	-3.2620926
199	Wielka Sala	B1	53.64491	-18.621475
200	Wielka Sala	B1	53.64491	-3.2620926
201	Wielka Sala	B2	2.947672	-2.8103445
202	Wielka Sala	B2	2.947672	-18.731276
203	Wielka Sala	B2	29.826595	-18.731276
204	Wielka Sala	B2	29.826595	-2.920145
205	Wielka Sala	B2	2.947672	11.984356
206	Wielka Sala	B2	29.826595	11.874556
207	Wielka Sala	B2	-24.27006	-18.621475
208	Wielka Sala	B2	-24.27006	-3.2620926
209	Wielka Sala	B2	56.592583	-18.621475
210	Wielka Sala	B2	56.592583	-3.2620926
211	Wielka Sala	B3	6.0680504	-2.8103445
212	Wielka Sala	B3	6.0680504	-18.731276
213	Wielka Sala	B3	32.94697	-18.731276
214	Wielka Sala	B3	32.94697	-2.920145
215	Wielka Sala	B3	6.0680504	11.984356
216	Wielka Sala	B3	32.94697	11.874556
217	Wielka Sala	B3	-21.149681	-18.621475
218	Wielka Sala	B3	-21.149681	-3.2620926
219	Wielka Sala	B3	59.71296	-18.621475
220	Wielka Sala	B3	59.71296	-3.2620926
221	Wielka Sala	B4	9.015722	-2.8103445
222	Wielka Sala	B4	9.015722	-18.731276
223	Wielka Sala	B4	35.89464	-18.731276
224	Wielka Sala	B4	35.89464	-2.920145
225	Wielka Sala	B4	9.015722	11.984356
226	Wielka Sala	B4	35.89464	11.874556
227	Wielka Sala	B4	-18.20201	-18.621475
228	Wielka Sala	B4	-18.20201	-3.2620926
229	Wielka Sala	B4	62.66063	-18.621475
230	Wielka Sala	B4	62.66063	-3.2620926
231	Wielka Sala	B5	12.251421	-2.8103445
232	Wielka Sala	B5	12.251421	-18.731276
233	Wielka Sala	B5	39.130344	-18.731276
234	Wielka Sala	B5	39.130344	-2.920145
235	Wielka Sala	B5	12.251421	11.984356
236	Wielka Sala	B5	39.130344	11.874556
237	Wielka Sala	B5	-14.9663105	-18.621475
238	Wielka Sala	B5	-14.9663105	-3.2620926
239	Wielka Sala	B5	65.89633	-18.621475
240	Wielka Sala	B5	65.89633	-3.2620926
241	Wielka Sala	B6	15.199093	-2.8103445
242	Wielka Sala	B6	15.199093	-18.731276
243	Wielka Sala	B6	42.078014	-18.731276
244	Wielka Sala	B6	42.078014	-2.920145
245	Wielka Sala	B6	15.199093	11.984356
246	Wielka Sala	B6	42.078014	11.874556
247	Wielka Sala	B6	-12.018639	-18.621475
248	Wielka Sala	B6	-12.018639	-3.2620926
249	Wielka Sala	B6	68.844	-18.621475
250	Wielka Sala	B6	68.844	-3.2620926
251	Wielka Sala	B7	18.319471	-2.8103445
252	Wielka Sala	B7	18.319471	-18.731276
253	Wielka Sala	B7	45.19839	-18.731276
254	Wielka Sala	B7	45.19839	-2.920145
255	Wielka Sala	B7	18.319471	11.984356
256	Wielka Sala	B7	45.19839	11.874556
257	Wielka Sala	B7	-8.89826	-18.621475
258	Wielka Sala	B7	-8.89826	-3.2620926
259	Wielka Sala	B7	71.96438	-18.621475
260	Wielka Sala	B7	71.96438	-3.2620926
261	Wielka Sala	B8	21.267143	-2.8103445
262	Wielka Sala	B8	21.267143	-18.731276
263	Wielka Sala	B8	48.146065	-18.731276
264	Wielka Sala	B8	48.146065	-2.920145
265	Wielka Sala	B8	21.267143	11.984356
266	Wielka Sala	B8	48.146065	11.874556
267	Wielka Sala	B8	-5.950588	-18.621475
268	Wielka Sala	B8	-5.950588	-3.2620926
269	Wielka Sala	B8	74.91205	-18.621475
270	Wielka Sala	B8	74.91205	-3.2620926
271	Wielka Sala	C1	0	-5.8167596
272	Wielka Sala	C1	0	-21.73769
273	Wielka Sala	C1	26.878922	-21.73769
274	Wielka Sala	C1	26.878922	-5.9265594
275	Wielka Sala	C1	0	8.9779415
276	Wielka Sala	C1	26.878922	8.868141
277	Wielka Sala	C1	-27.217731	-21.627892
278	Wielka Sala	C1	-27.217731	-6.268507
279	Wielka Sala	C1	53.64491	-21.627892
280	Wielka Sala	C1	53.64491	-6.268507
281	Wielka Sala	C2	2.947672	-5.8167596
282	Wielka Sala	C2	2.947672	-21.73769
283	Wielka Sala	C2	29.826595	-21.73769
284	Wielka Sala	C2	29.826595	-5.9265594
285	Wielka Sala	C2	2.947672	8.9779415
286	Wielka Sala	C2	29.826595	8.868141
287	Wielka Sala	C2	-24.27006	-21.627892
288	Wielka Sala	C2	-24.27006	-6.268507
289	Wielka Sala	C2	56.592583	-21.627892
290	Wielka Sala	C2	56.592583	-6.268507
291	Wielka Sala	C3	6.0680504	-5.8167596
292	Wielka Sala	C3	6.0680504	-21.73769
293	Wielka Sala	C3	32.94697	-21.73769
294	Wielka Sala	C3	32.94697	-5.9265594
295	Wielka Sala	C3	6.0680504	8.9779415
296	Wielka Sala	C3	32.94697	8.868141
297	Wielka Sala	C3	-21.149681	-21.627892
298	Wielka Sala	C3	-21.149681	-6.268507
299	Wielka Sala	C3	59.71296	-21.627892
300	Wielka Sala	C3	59.71296	-6.268507
301	Wielka Sala	C4	9.015722	-5.8167596
302	Wielka Sala	C4	9.015722	-21.73769
303	Wielka Sala	C4	35.89464	-21.73769
304	Wielka Sala	C4	35.89464	-5.9265594
305	Wielka Sala	C4	9.015722	8.9779415
306	Wielka Sala	C4	35.89464	8.868141
307	Wielka Sala	C4	-18.20201	-21.627892
308	Wielka Sala	C4	-18.20201	-6.268507
309	Wielka Sala	C4	62.66063	-21.627892
310	Wielka Sala	C4	62.66063	-6.268507
311	Wielka Sala	C5	12.251421	-5.8167596
312	Wielka Sala	C5	12.251421	-21.73769
313	Wielka Sala	C5	39.130344	-21.73769
314	Wielka Sala	C5	39.130344	-5.9265594
315	Wielka Sala	C5	12.251421	8.9779415
316	Wielka Sala	C5	39.130344	8.868141
317	Wielka Sala	C5	-14.9663105	-21.627892
318	Wielka Sala	C5	-14.9663105	-6.268507
319	Wielka Sala	C5	65.89633	-21.627892
320	Wielka Sala	C5	65.89633	-6.268507
321	Wielka Sala	C6	15.199093	-5.8167596
322	Wielka Sala	C6	15.199093	-21.73769
323	Wielka Sala	C6	42.078014	-21.73769
324	Wielka Sala	C6	42.078014	-5.9265594
325	Wielka Sala	C6	15.199093	8.9779415
326	Wielka Sala	C6	42.078014	8.868141
327	Wielka Sala	C6	-12.018639	-21.627892
328	Wielka Sala	C6	-12.018639	-6.268507
329	Wielka Sala	C6	68.844	-21.627892
330	Wielka Sala	C6	68.844	-6.268507
331	Wielka Sala	C7	18.319471	-5.8167596
332	Wielka Sala	C7	18.319471	-21.73769
333	Wielka Sala	C7	45.19839	-21.73769
334	Wielka Sala	C7	45.19839	-5.9265594
335	Wielka Sala	C7	18.319471	8.9779415
336	Wielka Sala	C7	45.19839	8.868141
337	Wielka Sala	C7	-8.89826	-21.627892
338	Wielka Sala	C7	-8.89826	-6.268507
339	Wielka Sala	C7	71.96438	-21.627892
340	Wielka Sala	C7	71.96438	-6.268507
341	Wielka Sala	C8	21.267143	-5.8167596
342	Wielka Sala	C8	21.267143	-21.73769
343	Wielka Sala	C8	48.146065	-21.73769
344	Wielka Sala	C8	48.146065	-5.9265594
345	Wielka Sala	C8	21.267143	8.9779415
346	Wielka Sala	C8	48.146065	8.868141
347	Wielka Sala	C8	-5.950588	-21.627892
348	Wielka Sala	C8	-5.950588	-6.268507
349	Wielka Sala	C8	74.91205	-21.627892
350	Wielka Sala	C8	74.91205	-6.268507
351	Wielka Sala	D1	0	-8.823175
352	Wielka Sala	D1	0	-24.744106
353	Wielka Sala	D1	26.878922	-24.744106
354	Wielka Sala	D1	26.878922	-8.932976
355	Wielka Sala	D1	0	5.971525
356	Wielka Sala	D1	26.878922	5.861725
357	Wielka Sala	D1	-27.217731	-24.634308
358	Wielka Sala	D1	-27.217731	-9.274923
359	Wielka Sala	D1	53.64491	-24.634308
360	Wielka Sala	D1	53.64491	-9.274923
361	Wielka Sala	D2	2.947672	-8.823175
362	Wielka Sala	D2	2.947672	-24.744106
363	Wielka Sala	D2	29.826595	-24.744106
364	Wielka Sala	D2	29.826595	-8.932976
365	Wielka Sala	D2	2.947672	5.971525
366	Wielka Sala	D2	29.826595	5.861725
367	Wielka Sala	D2	-24.27006	-24.634308
368	Wielka Sala	D2	-24.27006	-9.274923
369	Wielka Sala	D2	56.592583	-24.634308
370	Wielka Sala	D2	56.592583	-9.274923
371	Wielka Sala	D3	6.0680504	-8.823175
372	Wielka Sala	D3	6.0680504	-24.744106
373	Wielka Sala	D3	32.94697	-24.744106
374	Wielka Sala	D3	32.94697	-8.932976
375	Wielka Sala	D3	6.0680504	5.971525
376	Wielka Sala	D3	32.94697	5.861725
377	Wielka Sala	D3	-21.149681	-24.634308
378	Wielka Sala	D3	-21.149681	-9.274923
379	Wielka Sala	D3	59.71296	-24.634308
380	Wielka Sala	D3	59.71296	-9.274923
381	Wielka Sala	D4	9.015722	-8.823175
382	Wielka Sala	D4	9.015722	-24.744106
383	Wielka Sala	D4	35.89464	-24.744106
384	Wielka Sala	D4	35.89464	-8.932976
385	Wielka Sala	D4	9.015722	5.971525
386	Wielka Sala	D4	35.89464	5.861725
387	Wielka Sala	D4	-18.20201	-24.634308
388	Wielka Sala	D4	-18.20201	-9.274923
389	Wielka Sala	D4	62.66063	-24.634308
390	Wielka Sala	D4	62.66063	-9.274923
391	Wielka Sala	D5	12.251421	-8.823175
392	Wielka Sala	D5	12.251421	-24.744106
393	Wielka Sala	D5	39.130344	-24.744106
394	Wielka Sala	D5	39.130344	-8.932976
395	Wielka Sala	D5	12.251421	5.971525
396	Wielka Sala	D5	39.130344	5.861725
397	Wielka Sala	D5	-14.9663105	-24.634308
398	Wielka Sala	D5	-14.9663105	-9.274923
399	Wielka Sala	D5	65.89633	-24.634308
400	Wielka Sala	D5	65.89633	-9.274923
401	Wielka Sala	D6	15.199093	-8.823175
402	Wielka Sala	D6	15.199093	-24.744106
403	Wielka Sala	D6	42.078014	-24.744106
404	Wielka Sala	D6	42.078014	-8.932976
405	Wielka Sala	D6	15.199093	5.971525
406	Wielka Sala	D6	42.078014	5.861725
407	Wielka Sala	D6	-12.018639	-24.634308
408	Wielka Sala	D6	-12.018639	-9.274923
409	Wielka Sala	D6	68.844	-24.634308
410	Wielka Sala	D6	68.844	-9.274923
411	Wielka Sala	D7	18.319471	-8.823175
412	Wielka Sala	D7	18.319471	-24.744106
413	Wielka Sala	D7	45.19839	-24.744106
414	Wielka Sala	D7	45.19839	-8.932976
415	Wielka Sala	D7	18.319471	5.971525
416	Wielka Sala	D7	45.19839	5.861725
417	Wielka Sala	D7	-8.89826	-24.634308
418	Wielka Sala	D7	-8.89826	-9.274923
419	Wielka Sala	D7	71.96438	-24.634308
420	Wielka Sala	D7	71.96438	-9.274923
421	Wielka Sala	D8	21.267143	-8.823175
422	Wielka Sala	D8	21.267143	-24.744106
423	Wielka Sala	D8	48.146065	-24.744106
424	Wielka Sala	D8	48.146065	-8.932976
425	Wielka Sala	D8	21.267143	5.971525
426	Wielka Sala	D8	48.146065	5.861725
427	Wielka Sala	D8	-5.950588	-24.634308
428	Wielka Sala	D8	-5.950588	-9.274923
429	Wielka Sala	D8	74.91205	-24.634308
430	Wielka Sala	D8	74.91205	-9.274923
\.


--
-- Name: actors_actor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.actors_actor_id_seq', 1, true);


--
-- Name: auth_white_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auth_white_list_id_seq', 1, false);


--
-- Name: performance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.performance_id_seq', 1, false);


--
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 9, true);


--
-- Name: seances_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seances_id_seq', 21, true);


--
-- Name: seats_seat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seats_seat_id_seq', 430, true);


--
-- Name: actors actors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors
    ADD CONSTRAINT actors_pkey PRIMARY KEY (actor_id);


--
-- Name: auth_white_list auth_white_list_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auth_white_list
    ADD CONSTRAINT auth_white_list_pkey PRIMARY KEY (id);


--
-- Name: halls halls_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.halls
    ADD CONSTRAINT halls_pkey PRIMARY KEY (hall_name);


--
-- Name: performance performance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance
    ADD CONSTRAINT performance_pkey PRIMARY KEY (id);


--
-- Name: cast pk_cast; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."cast"
    ADD CONSTRAINT pk_cast PRIMARY KEY (actor_id, performance_id);


--
-- Name: reservedseats pk_reservedseats; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservedseats
    ADD CONSTRAINT pk_reservedseats PRIMARY KEY (id_reservation, seat_id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- Name: seances seances_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seances
    ADD CONSTRAINT seances_pkey PRIMARY KEY (id);


--
-- Name: seats seats_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_pkey PRIMARY KEY (seat_id);


--
-- Name: cast fk_cast_actor_id__actor_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."cast"
    ADD CONSTRAINT fk_cast_actor_id__actor_id FOREIGN KEY (actor_id) REFERENCES public.actors(actor_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: cast fk_cast_performance_id__id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."cast"
    ADD CONSTRAINT fk_cast_performance_id__id FOREIGN KEY (performance_id) REFERENCES public.performance(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: reservation fk_reservation_id_seance__id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk_reservation_id_seance__id FOREIGN KEY (id_seance) REFERENCES public.seances(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: reservedseats fk_reservedseats_id_reservation__id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservedseats
    ADD CONSTRAINT fk_reservedseats_id_reservation__id FOREIGN KEY (id_reservation) REFERENCES public.reservation(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: reservedseats fk_reservedseats_seat_id__seat_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservedseats
    ADD CONSTRAINT fk_reservedseats_seat_id__seat_id FOREIGN KEY (seat_id) REFERENCES public.seats(seat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: seances fk_seances_hall_id__hall_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seances
    ADD CONSTRAINT fk_seances_hall_id__hall_name FOREIGN KEY (hall_id) REFERENCES public.halls(hall_name) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: seances fk_seances_performance_id__id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seances
    ADD CONSTRAINT fk_seances_performance_id__id FOREIGN KEY (performance_id) REFERENCES public.performance(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: seats fk_seats_hall_name__hall_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seats
    ADD CONSTRAINT fk_seats_hall_name__hall_name FOREIGN KEY (hall_name) REFERENCES public.halls(hall_name) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

