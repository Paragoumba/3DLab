let debug = true;
const debugElts = {
    fps: document.getElementById("fps"),
    pos: document.getElementById("pos"),
    rot: document.getElementById("rot"),
    info: document.getElementById("info")
}

const loader = new THREE.TextureLoader();
const movement = {
    forward: false,
    left: false,
    backward: false,
    right: false,
}

const materials = {
    white: new THREE.MeshPhongMaterial({color: 0xFFFFFF}),
    black: new THREE.MeshPhongMaterial({color: 0x000000}),
    red: new THREE.MeshPhongMaterial({color: 0xFF0000}),
    green: new THREE.MeshPhongMaterial({color: 0x00FF00}),
    blue: new THREE.MeshPhongMaterial({color: 0x0000FF}),
    yellow: new THREE.MeshPhongMaterial({color: 0xFFFF00}),
    cyan: new THREE.MeshPhongMaterial({color: 0x00FFFF}),
    magenta: new THREE.MeshPhongMaterial({color: 0xFF00FF}),
    brick: new THREE.MeshPhongMaterial({map: loader.load('imgs/brick.png')}),
    stone: new THREE.MeshPhongMaterial({map: loader.load('imgs/stone.png')}),
    icon: new THREE.MeshPhongMaterial({map: loader.load('imgs/3dlab-icon.png')})
}
const textElt = document.getElementById("text");

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(60, window.innerWidth / window.innerHeight, 0.1, 1000);

const renderer = new THREE.WebGLRenderer({antialias: false});
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

const controls = new THREE.PointerLockControls(camera, renderer.domElement);

renderer.domElement.addEventListener('click', function(){

    controls.lock();

}, false);

window.addEventListener('resize', function(){
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
});

scene.background = new THREE.CubeTextureLoader().load(['imgs/cloudtop_ft.png', 'imgs/cloudtop_bk.png', 'imgs/cloudtop_up.png', 'imgs/cloudtop_dn.png', 'imgs/cloudtop_rt.png', 'imgs/cloudtop_lf.png']);
scene.add(new THREE.AmbientLight(0xAAAAAA));

var light = new THREE.PointLight();
light.castShadow = true;
light.shadow.camera.zoom = 4; // tighter shadow map
scene.add(light);

let lastFPS = 0;
let fps = 0;
let last = new Date().getTime();

function keyEvent(event, pressed){

    switch (event.keyCode){

        case 90: // z: Move Forward
            movement.forward = pressed;
            break;

        case 81: // q: Move left
            movement.left = pressed;
            break;

        case 83: // s: Move backward
            movement.backward = pressed;
            break;

        case 68: // d: Move right
            movement.right = pressed;
            break;

        case 67: // c: Go up
            movement.up = pressed;
            break;

        case 88: // x: Go down
            movement.down = pressed;
            break;

        case 73: // i: Display debug
            if (pressed){

                debug = !debug;

                for (let [, value] of Object.entries(debugElts)){

                    if (!debug){

                        value.textContent = "";
                        value.innerHeight = "";
                        value.style.display = "hidden";

                    } else {

                        value.style.display = "";

                    }
                }
            }

            break;

        default:
            if (debug && pressed) console.log("Key '" + String.fromCharCode(event.which) + "' (" + event.keyCode + ")");

    }
}

document.addEventListener( 'keydown', (ev) => {keyEvent(ev, true)}, false);
document.addEventListener( 'keyup', (ev) => {keyEvent(ev, false)}, false);

function animate(){

    requestAnimationFrame(animate);

    let now = new Date().getTime();

    if (controls.isLocked === true){

        if (movement.forward){

            controls.moveForward(1);

        }

        if (movement.left){

            controls.moveRight(-1);

        }

        if (movement.backward){

            controls.moveForward(-1);

        }

        if (movement.right){

            controls.moveRight(1);

        }

        //light.position.set(camera.position.x, camera.position.y, camera.position.z);

    }

    if (now - last > 1000){

        last = now;
        lastFPS = fps;
        fps = 0;

        if (debug){

            debugElts.info.textContent = "Geo: " + renderer.info.memory.geometries + " Tex: " + renderer.info.memory.textures
                + " Tr: " + renderer.info.render.triangles;
            debugElts.fps.textContent = "FPS: " + lastFPS + " Frame: " + renderer.info.render.frame;

        }
    }

    if (debug){

        debugElts.pos.textContent = "x:" + (camera.position.x).toFixed(2) + " y:" + (camera.position.y).toFixed(2) + " z:" + (camera.position.z).toFixed(2);
        debugElts.rot.textContent = "rx:" + (180 / Math.PI * camera.rotation.x).toFixed(2) + " ry:" + (180 / Math.PI * camera.rotation.y).toFixed(2) + " rz:" + (180 / Math.PI * camera.rotation.z).toFixed(2);

    }

    renderer.render(scene, camera);
    ++fps;

}

let levels;

importData();

function importData(){

    fetch("data.json")
        .then(data => data.json())
        .then(data => importLab(data.levels))
        .then(animate)
        .catch(error => {
            debugElts.info.textContent = "Could not load data.";
            console.error(error);
        });

}

function importLab(data){

    levels = [];

    let i = 0;
    for (const level of data.levels){

        const lab = level.lab;
        const newLevel = levels[i] = {
            lab: {
                width: lab.width,
                height: lab.height,
                rows: []
            }
        };

        for (let r = 0; r < newLevel.lab.width; ++r){

            newLevel.lab.rows[r] = [];

        }

        const boxGeometry = new THREE.BoxGeometry();

        for (const block of lab.walls){

            let material = materials[block.mat];

            if (material === undefined){

                material = materials.brick;

            }

            const cube = new THREE.Mesh(boxGeometry, material);

            cube.position.x = block.x;
            cube.position.z = block.y;

            newLevel.lab.rows[block.x][block.y] = cube;

        }

        const planeGeometry = new THREE.PlaneGeometry();
        const material = materials.stone;

        for (let x = 0; x < newLevel.lab.width; ++x){
            for (let y = 0; y < newLevel.lab.height; ++y){

                let object = newLevel.lab.rows[x][y];

                if (object === undefined){

                    object = new THREE.Mesh(planeGeometry, material);

                    object.geometry.side = THREE.DoubleSide;

                    object.position.x = x;
                    object.position.y = -0.5;
                    object.position.z = y;
                    object.rotation.x = -Math.PI / 2;
                    newLevel.lab.rows[x][y] = object;

                }

                scene.add(object);

            }
        }

        ++i;

    }

    const blockGeo = new THREE.BoxGeometry();
    const block = new THREE.Mesh(blockGeo, materials.white);

    scene.add(block);
    block.position.x = levels[0].lab.width * 2;
    block.position.y = 30;
    block.position.z = -levels[0].lab.height;

    camera.position.set(3, 0, 1);
    camera.rotation.y = Math.PI / 180 * -90;

    light.position.set(levels[0].lab.width * 2, 30, -levels[0].lab.height);

}

for (let materialsKey in materials){

    materials[materialsKey].dispose();

}

for (let texturesKey in textures){

    textures[texturesKey].dispose();

}

for (let object of scene.children){

    //object.dispose();

}