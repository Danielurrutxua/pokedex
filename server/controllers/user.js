import User from '../models/user.js'

export const registerUser = async (req, res, next)=> {
    
    const user = req.body;
    const newUser = new User(user);
    console.log(user)

    try {
        const userByUsername = await User.findOne({username: user.username})
        const userByEmail = await User.findOne({email: user.email })

        if(userByUsername == null){
            if(userByEmail == undefined){
                await newUser.save();
                res.status(200).json(user)
            }else{
                res.status(400).json(user)
            }
        }else{
            res.status(401).json(user)
        }
        
    }catch(error) {
        res.status(409).json({message: error.message})
    }
        
    
}

export const  loginUser = async (req, res, next) => {
    try {
        const user = await User.findOne({username: req.body.username});
       
        if(user.password == req.body.password){

            res.status(200).json(user);

        } else {
            
            res.status(406).json("Username or password is incorrect")
        }
    } catch (error) {
        res.status(404).json({error: error.message})
    }
}