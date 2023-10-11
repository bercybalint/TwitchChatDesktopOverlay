const tmi = require('tmi.js');
const Stream = require('stream');

// Define configuration options
const opts = {
    identity: {
        username: 'username',
        password: 'password'
    },
    channels: [
        'mychreme'
    ]
};

// Create a client with our options
const client = new tmi.client(opts);
const readableStream = new Stream.Readable({
    read(size) {
        return true;
    }
});

// Register our event handlers (defined below)
client.on('message', onMessageHandler);
client.on('connected', onConnectedHandler);

// Connect to Twitch:
client.connect();

// Called every time a message comes in
function onMessageHandler (target, context, msg, self) {
    if (self) { return; } // Ignore messages from the bot
    console.log(msg);
    readableStream.push(msg);
}


// Called every time the bot connects to Twitch chat
function onConnectedHandler (addr, port) {
    //console.log(`* Connected to ${addr}:${port}`);
}
