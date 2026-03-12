# Event Management API

Backend API desarrollada con **Java y Spring Boot** para gestionar eventos y reservas de alojamiento asociadas a los eventos.

El objetivo del proyecto es practicar **arquitectura backend**, **diseño de APIs REST** y **modelado de dominio con JPA/Hibernate**.

---

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- SQL Database
- Docker (base de datos)
- Maven
- Git

---

## Arquitectura

El proyecto sigue una arquitectura clásica en capas:

Controller → Service → Repository → Database

- **Controllers**: exponen los endpoints REST
- **Services**: contienen la lógica de negocio
- **Repositories**: acceso a datos con Spring Data JPA
- **Entities**: modelo de dominio y relaciones entre entidades

---

## Modelo de dominio

Entidades principales del sistema:

- User
- Person
- Event
- Registration
- Hotel
- Room
- Booking

El sistema permite:

- registrar usuarios en eventos
- gestionar eventos
- reservar habitaciones de hotel asociadas a eventos

---

## Relaciones principales

Ejemplos de relaciones modeladas:

- User → Registration → Event
- Hotel → Room
- User → Booking → Room

Las relaciones están implementadas utilizando **JPA/Hibernate** (OneToMany, ManyToOne, etc.).

---

## Funcionalidades implementadas

Actualmente el proyecto incluye:

- Modelado de entidades con JPA
- Relaciones entre entidades
- Repositories con Spring Data JPA
- Capa de servicios con lógica de negocio
- Controllers REST
- Base de datos SQL ejecutándose en Docker

---

## Próximos pasos

- Implementar seguridad con Spring Security
- Añadir testing de servicios
- Mejorar la gestión de roles y permisos
- Añadir validación de datos en los endpoints

---

## Objetivo del proyecto

Este proyecto forma parte de mi proceso de aprendizaje para reforzar mis habilidades como **Backend Developer con Java y Spring Boot**, trabajando en:

- diseño de APIs REST
- modelado de dominio
- arquitectura backend
- buenas prácticas en desarrollo de software

---

## Autor

Albert Ramos  
Backend Developer  
Barcelona
