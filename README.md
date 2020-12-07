# WebService - Java - Computacao Distribuida - UFMS

## Objetivo
Implementar um WebService sem frameworks

## Para executar

*Necessário ter Docker e Docker Compose*
```
git clone https://github.com/mateusragazzi/Comp-Distribuida.git
```

Entre na pasta do projeto:
```
cd Comp-Distribuida
```
### Local
```
docker-compose up -d
```
### Com logs
```
docker-compose up 
```
## Endpoints
#### actors
* GET /actors
* GET /actors/{id}
* POST /actors
* PUT /actors/{id}
* DELETE /actors/{id}
#### movies
* GET /movies
* GET /movies/{id}
* GET /movies/{id}/actors (todos atores de um determinado filme)
* POST /movies
* PUT /movies/{id}
* DELETE /movies/{id}
#### busca filmes e atores
* GET /search/{search_term}
```
