
<!DOCTYPE html>
<html>

<head>
    <title>Audio Dropdown</title>
</head>

<body>
  <h1>Audio Dropdown</h1>
      <label for="audio-select">Select an audio:</label>
      <select id="audio-select">
        <option value="" disabled selected>Select an audio</option>
        <option value="Shakira.mp3">Audio 1</option>
        <option value="Maroon 5 - Sugar (Official Music Video).mp3">Audio 2</option>
        <option value="Jhené Aiko - Lead the Way (From 'Raya and the Last Dragon'-Lyric Video).mp3">Audio 3</option>
        <option value="Enrique Iglesias - Somebody's Me.mp3">Audio 4</option>
        <option value="Enrique Iglesias - Nunca Te Olvidaré (Official Music Video).mp3">Audio 5</option>
    </select>


    <audio controls audioplay id="audio-player" style="display:none">
            <source id="audio-source" src="" controls type="audio/mpeg"></source>
            Your browser does not support the audio element.
        </audio>

        <script>
            document.getElementById('audio-select').addEventListener('change', function () {
                const audioSelect = this;
                const audioPlayer = document.getElementById('audio-player');
                const audioSource = document.getElementById('audio-source');

                const selectedAudio = audioSelect.value;
                if (selectedAudio) {
                    audioSource.src = selectedAudio;
                    audioPlayer.load();
                    audioPlayer.play();
                }
            });
        </script>


     <form action="index.jsp">
             <br/>
             <input type="submit" value="Back" class="btn btn-primary btn-block"/>
             </form>

     <br></br>
     <br></br>


             <h1>Login</h1>
                 <form action="login" method="post">
                     <label for="username">Username:</label>
                     <input type="text" id="username" name="username" required><br>
                     <label for="password">Password:</label>
                     <input type="password" id="password" name="password" required><br>
                     <input type="submit" value="Login">
                 </form>
</body>


</html>