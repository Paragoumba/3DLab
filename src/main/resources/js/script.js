let debug = false;
const debugElts = {
    fps: document.getElementById("fps"),
    pos: document.getElementById("pos"),
    rot: document.getElementById("rot"),
    info: document.getElementById("info")
}

const message = document.getElementById("message");

const loader = new THREE.TextureLoader();
const movement = {
    forward: false,
    left: false,
    backward: false,
    right: false
}

const textures = {
    brick: loader.load('imgs/brick.png'),
    stone: loader.load('imgs/stone.png'),
    icon: loader.load('imgs/3dlab-icon.png')
}

textures.brick.anistropy = 0;
textures.stone.anistropy = 0;
textures.icon.anistropy = 0;
textures.stone.minFilter = textures.brick.minFilter = textures.icon.minFilter = THREE.NearestFilter;
textures.brick.magFilter = textures.stone.magFilter = textures.icon.magFilter = THREE.NearestFilter;

const materials = {
    "#FFFFFF": new THREE.MeshPhongMaterial({color: 0xFFFFFF}),
    "#000000": new THREE.MeshPhongMaterial({color: 0x000000}),
    "#FF0000": new THREE.MeshPhongMaterial({color: 0xFF0000}),
    "#00FF00": new THREE.MeshPhongMaterial({color: 0x00FF00}),
    "#0000FF": new THREE.MeshPhongMaterial({color: 0x0000FF}),
    "#FFFF00": new THREE.MeshPhongMaterial({color: 0xFFFF00}),
    "#00FFFF": new THREE.MeshPhongMaterial({color: 0x00FFFF}),
    "#FF00FF": new THREE.MeshPhongMaterial({color: 0xFF00FF}),
    brick: new THREE.MeshPhongMaterial({map: textures.brick, shininess: 0}),
    stone: new THREE.MeshPhongMaterial({map: textures.stone, shininess: 0}),
    icon: new THREE.MeshPhongMaterial({map: textures.icon})
}

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(60, window.innerWidth / window.innerHeight, 0.1, 1000);

const renderer = new THREE.WebGLRenderer({antialias: false});
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

const controls = new THREE.PointerLockControls(camera, renderer.domElement);

window.addEventListener('resize', function(){
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
});

scene.background = new THREE.CubeTextureLoader().load(['imgs/cloudtop_ft.png', 'imgs/cloudtop_bk.png', 'imgs/cloudtop_up.png', 'imgs/cloudtop_dn.png', 'imgs/cloudtop_rt.png', 'imgs/cloudtop_lf.png']);
scene.add(new THREE.AmbientLight(0xAAAAAA));

const light = new THREE.PointLight();
light.castShadow = true;
light.shadow.camera.zoom = 4; // tighter shadow map
scene.add(light);

let lastFPS = 0;
let fps = 0;
let last = new Date().getTime();

const raycaster = new THREE.Raycaster();

raycaster.near = -0.5;
raycaster.far = 0.5;

const objects = [];

function addObject(object){

    objects.push(object);
    scene.add(object);

}

function clearObjects(){

    for (const object of objects){

        scene.remove(object);

    }

    objects.length = 0;

}

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

            if (debug){

                movement.up = pressed;

            }
            break;

        case 88: // x: Go down

            if (debug){

                movement.down = pressed;

            }
            break;

        case 107: // +: Go to next level

            if (debug){

                setCurrentLevel(currentLevel + 1);

            }
            break;

        case 109: // -: Go to previous level

            if (debug){

                setCurrentLevel(currentLevel - 1);

            }
            break;

        case 73: // i: Toggle debug and displays info
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
            if (debug && pressed){

                console.log("Key '" + String.fromCharCode(event.which) + "' (" + event.keyCode + ")");

            }
    }
}

document.addEventListener( 'keydown', (ev) => {keyEvent(ev, true)}, false);
document.addEventListener( 'keyup', (ev) => {keyEvent(ev, false)}, false);

const velocity = 0.1;

/* This is the rendering loop. It is executed after the levels have been imported. It handles all the rendering,
** the inputs, the collisions and the debug displaying.
**/
function animate(){

    requestAnimationFrame(animate);

    const now = new Date().getTime();
    const direction = new THREE.Vector2();

    if (controls.isLocked){

        if (movement.forward){

            direction.y += velocity;

        }

        if (movement.left){

            direction.x -= velocity;

        }

        if (movement.backward){

            direction.y -= velocity;

        }

        if (movement.right){

            direction.x += velocity;

        }
    }

    const lookVec = new THREE.Vector3();
    camera.getWorldDirection(lookVec);
    lookVec.applyAxisAngle(new THREE.Vector3(0, 1, 0), Math.atan2(-direction.x, direction.y));
    raycaster.set(camera.position, lookVec);

    let intersects = raycaster.intersectObjects(objects);
    let collision = false;

    if (intersects.length > 0){

        for (const intersect of intersects){

            if (intersect.distance < 0.5){

                collision = true;
                break;

            }
        }
    }

    if (!collision && controls.isLocked){

        if (movement.forward){

            controls.moveForward(velocity);

        }

        if (movement.left){

            controls.moveRight(-velocity);

        }

        if (movement.backward){

            controls.moveForward(-velocity);

        }

        if (movement.right){

            controls.moveRight(velocity);

        }

        if (movement.up){

            camera.position.y += velocity;

        }

        if (movement.down){

            camera.position.y -= velocity;

        }
    }

    const level = levels[currentLevel];

    if (!debug &&
        camera.position.x >= level.lab.end.x - 0.5 && camera.position.x < level.lab.end.x + 0.5 &&
        camera.position.z >= level.lab.end.y - 0.5 && camera.position.z < level.lab.end.y + 0.5){

        nextLevel();

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

/* Here we import the labyrinths from the data.json file. If no error is encountered the animation loop is executed.
** Else an error message is displayed. All the imported levels are stored in the levels variable.
**/
let levels;
let currentLevel;

importData();

function importData(){

    fetch("data.json")
        .then(data => data.json())
        .then(data => importLab(data))
        .then(() => renderer.domElement.addEventListener('click', () => controls.lock(), false))
        .then(() => nextLevel())
        .then(animate)
        .catch(error => {
            debugElts.info.textContent = "Could not load data.";
            console.error(error);
        });
}

function importLab(data){

    clearObjects();
    levels = [];

    let i = 0;
    for (const level of data.levels){

        const lab = level.lab;
        const newLevel = levels[i] = {
            lab: {
                width: lab.width,
                height: lab.height,
                start: lab.start,
                end: lab.end,
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
            }
        }

        ++i;

    }
}

function setCurrentLevel(i){

    if (i < 0 || i >= levels.length){

        return false;

    }

    currentLevel = i;
    clearObjects();

    const level = levels[i];

    for (let x = 0; x < level.lab.width; ++x){
        for (let y = 0; y < level.lab.height; ++y){

            let object = level.lab.rows[x][y];

            addObject(object);

        }
    }

    light.position.set(level.lab.width * 2, 30, -level.lab.height);

    const endGeo = new THREE.PlaneGeometry();
    const end = new THREE.Mesh(endGeo, materials.icon);
    end.position.x = level.lab.end.x;
    end.position.y = -0.5;
    end.position.z = level.lab.end.y;
    end.rotation.x = -Math.PI / 2;

    addObject(end);

    return true;

}

function nextLevel(){

    const success = setCurrentLevel(currentLevel !== undefined ? currentLevel + 1 : 0);

    if (!success){

        message.textContent = "You win!";
        message.style.display = "inherit";

        return success;

    }

    message.textContent = "Level " + currentLevel + "!";
    message.style.display = "inherit";

    setTimeout(() => message.style.display = "none", 3000);

    camera.position.set(levels[currentLevel].lab.start.x, 0, levels[currentLevel].lab.start.y);
    camera.rotation.set(0, Math.PI, 0);

    return success;

}