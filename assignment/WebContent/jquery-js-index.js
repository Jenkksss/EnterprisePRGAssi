//sends the datatype

function getFormat() {
	if (document.getElementById('xmlButton').checked) {
		return "xml";
	} else if (document.getElementById('jsonButton').checked) {
		return "json";
	} else if (document.getElementById('plainTxtButton').checked) {
		return "plainTxt";
	} else {
		return "json";
	}
}

//Get data update film


function makeUpdateString() {
	var fullString =
		"title=" + getTitle() +
		"&id=" + getId() +
		"&year=" + getYear() +
		"&director=" + getDirector() +
		"&stars=" + getStars() +
		"&review=" + getReview();
	return(fullString);
}

function getFilmUpdate() {
  return(escape(document.getElementById("filmUpdate").value));
}

function getTitleUpdate() {
  return(escape(document.getElementById("titleUpdate").value));
}

function getIdUpdate() {
  return(escape(document.getElementById("idUpdate").value));
}

function getYearUpdate() {
  return(escape(document.getElementById("yearUpdate").value));
}

function getDirectorUpdate() {
  return(escape(document.getElementById("directorUpdate").value));
}

function getStarsUpdate() {
  return(escape(document.getElementById("starsUpdate").value));
}

function getReviewUpdate() {
  return(escape(document.getElementById("reviewUpdate").value));
}
//Update film function
function updateFilm() {
	var data = makeUpdateString();
	jQuery.ajax({
		type:"POST",
		url:"update-film",
		data:data,
		success:function(){
			$("#result-update-modal").append("Film Updated")
		}
	})
}

//Get data for inserting a film
function makeInsertString() {
	var insertString =
		"title=" + getTitleInsert() +
		"&year=" + getYearInsert() +
		"&director=" + getDirectorInsert() +
		"&stars=" + getStarsInsert() +
		"&review=" + getReviewInsert();
	return(insertString);
}


function getTitleInsert() {
  return(escape(document.getElementById("titleInsert").value));
}

function getYearInsert() {
  return(escape(document.getElementById("yearInsert").value));
}

function getDirectorInsert() {
  return(escape(document.getElementById("directorInsert").value));
}

function getStarsInsert() {
  return(escape(document.getElementById("starsInsert").value));
}

function getReviewInsert() {
  return(escape(document.getElementById("reviewInsert").value));
}








function makeAllFilmsString() {
  var formatString = "format=" + getFormat();
  return(formatString);
}

//these functions determine the datatype
function getAllFilms() {
	format = getFormat();
	console.log(format);
	if (format==="xml"){
		getAllFilmsXML();
	}
	else if(format==="plainTxt"){
		getAllFilmsText();
	}
	else if(format==="json"){
		getAllFilmsJSON();
	}
		else{
		getAllFilmsJSON();
	}
}


//Get All Films implentations
function getAllFilmsJSON() {
	var format = makeAllFilmsString();

	jQuery.ajax({
		type:"GET",
		url:"get-all-films",
		data:format,
		success:function(data){
			var json = JSON.parse(data);
			$('#results').empty();
			if(json != null){
		    for (var i = 0; i < json.length; i++) {
				var id = json[i].id;
				var title = json[i].title;
				var year = json[i].year;
				var director = json[i].director;
				var stars = json[i].stars;
				var review = json[i].review;
			    $('#results').append("<li>Film ID: "+id + "<br>Title: " + title + "<br>Year: " + year + "<br>Director: " + director +"<br>Stars: " + stars +"<br>Review: " + review+"</li>");
		    }
		}
		}

	})
}

function getAllFilmsText() {
	var format = makeAllFilmsString();
	jQuery.ajax({
		type:"GET",
		url:"get-all-films",
		data:format,
		success:function(data){
			$('#results').empty();
			$('#results').append(data);
		}
	})
}

function getAllFilmsXML() {
	format = makeAllFilmsString();
	jQuery.ajax({
		type:"GET",
		url:"get-all-films",
		data:format,
		success:function(data){
			$('#results').empty();
			if(data != null){
				$(data).find('film').each(function(){
				var id = $(this).find('id').text();
				var title = $(this).find('title').text();
				var year = $(this).find('year').text();
				var director = $(this).find('director').text();
				var stars = $(this).find('stars').text();
				var review = $(this).find('review').text();
				$('#results').append("<li>Film ID: "+id+" <br> Title: "+title+" <br>Year: "+year+" <br>Director: "+director+" <br>Stars: "+stars+" <br>Review: "+review+"</li>");
			});
			}
		}
	})
}


//Get film functions

function getFilmByTitle() {
	format = getFormat();
	console.log(format);
	if (format==="xml"){
		getFilmXML();
	}
	else if(format==="json"){
		getFilmJSON();
	}
		else if(format==="plainTxt"){
		getFilmText();
	}
	else{
		getFilmJSON();
	}
}


function makeOneFilmString() {
  var titleString =
    "title=" + getFilm() +
    "&format=" + getFormat();
  return(titleString);
}

function getFilmJSON() {
		var format = makeOneFilmString();

		jQuery.ajax({
			type:"GET",
			url:"get-film",
			data:format,
			success:function(data){
				$('#results').empty();
				var json = JSON.parse(data);
				if(json != null){
			    for (var i = 0; i < json.length; i++) {
					var id = json[i].id;
					var title = json[i].title;
					var year = json[i].year;
					var director = json[i].director;
					var stars = json[i].stars;
					var review = json[i].review;
			       $('#results').append("<li>Film ID: "+id + "<br>Title: " + title + "<br>Year: " + year + "<br>Director: " + director +"<br>Stars: " + stars +"<br>Review: " + review+"</li>");
			    }
			}
			}

		})
}

function getFilmXML() {
	format = makeOneFilmString();
	jQuery.ajax({
		type:"GET",
		url:"get-film",
		data:format,
		success:function(data){
			if(data != null){
				$('#results').empty();
				$(data).find('film').each(function(){
				var id = $(this).find('id').text();
				var title = $(this).find('title').text();
				var year = $(this).find('year').text();
				var director = $(this).find('director').text();
				var stars = $(this).find('stars').text();
				var review = $(this).find('review').text();
				$('#results').append("<li>Film ID: "+id+" <br> Title: "+title+" <br>Year: "+year+" <br>Director: "+director+" <br>Stars: "+stars+" <br>Review: "+review+"</li>");
			});
			}
		}
	})
}

function getFilmText() {
	var format = makeOneFilmString();

	jQuery.ajax({
		type:"GET",
		url:"get-film",
		data:format,
		success:function(data){
			$('#results').empty();
			$('#results').append(data);
		}
	})
}



//Insert film function
function insertFilm() {
	var data = makeInsertString();
	jQuery.ajax({
		type:"POST",
		url:"insert-film",
		data:data,
		success:function(){
			$("#result-insert-modal").append("Film Inserted")
		}
	})
}

//Delete film function
function makeDeleteString() {
  var idString =
    "id=" + getIdDelete()
  return(idString);
}
//Get ID for film to be deleted
function getIdDelete() {
  return(escape(document.getElementById("idDelete").value));
}

function deleteFilm() {
	var data = makeDeleteString();
	jQuery.ajax({
		type:"POST",
		url:"delete-film",
		data:data,
		success:function(){
			$("#result-delete-modal").append("Film Deleted")
		}
	})
}
