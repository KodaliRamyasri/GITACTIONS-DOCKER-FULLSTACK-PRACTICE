import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';

const FitnessUserManager = () => {
  const [users, setUsers] = useState([]);
  const [user, setUser] = useState({
    id: '',
    name: '',
    email: '',
    age: '',
    gender: '',
    workoutType: '',
    duration: '',
    caloriesBurned: '',
    intensity: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedUser, setFetchedUser] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  const baseUrl = `${import.meta.env.VITE_API_URL}/fitnessapi`;

  useEffect(() => {
    fetchAllUsers();
  }, []);

  const fetchAllUsers = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setUsers(res.data);
    } catch (error) {
      setMessage('Failed to fetch users.');
    }
  };

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    for (let key in user) {
      if (!user[key] || user[key].toString().trim() === '') {
        setMessage(`Please fill out the ${key} field.`);
        return false;
      }
    }
    return true;
  };

  const addUser = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, user);
      setMessage('User added successfully.');
      fetchAllUsers();
      resetForm();
    } catch (error) {
      setMessage('Error adding user.');
    }
  };

  const updateUser = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, user);
      setMessage('User updated successfully.');
      fetchAllUsers();
      resetForm();
    } catch (error) {
      setMessage('Error updating user.');
    }
  };

  const deleteUser = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllUsers();
    } catch (error) {
      setMessage('Error deleting user.');
    }
  };

  const getUserById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedUser(res.data);
      setMessage('');
    } catch (error) {
      setFetchedUser(null);
      setMessage('User not found.');
    }
  };

  const handleEdit = (usr) => {
    setUser(usr);
    setEditMode(true);
    setMessage(`Editing user with ID ${usr.id}`);
  };

  const resetForm = () => {
    setUser({
      id: '',
      name: '',
      email: '',
      age: '',
      gender: '',
      workoutType: '',
      duration: '',
      caloriesBurned: '',
      intensity: ''
    });
    setEditMode(false);
  };

  return (
    <div className="fitness-container">

      {message && <div className={`message ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>{message}</div>}

      <h2>Fitness Tracker Manager</h2>

      <div className="form-section">
        <h3>{editMode ? 'Edit User' : 'Add New User'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="ID" value={user.id} onChange={handleChange} />
          <input type="text" name="name" placeholder="Full Name" value={user.name} onChange={handleChange} />
          <input type="email" name="email" placeholder="Email" value={user.email} onChange={handleChange} />
          <input type="number" name="age" placeholder="Age" value={user.age} onChange={handleChange} />
          <select name="gender" value={user.gender} onChange={handleChange}>
            <option value="">Select Gender</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
            <option value="OTHER">Other</option>
          </select>
          <input type="text" name="workoutType" placeholder="Workout Type (e.g., Running)" value={user.workoutType} onChange={handleChange} />
          <input type="number" name="duration" placeholder="Duration (in minutes)" value={user.duration} onChange={handleChange} />
          <input type="number" name="caloriesBurned" placeholder="Calories Burned" value={user.caloriesBurned} onChange={handleChange} />
          <select name="intensity" value={user.intensity} onChange={handleChange}>
            <option value="">Select Intensity</option>
            <option value="LOW">Low</option>
            <option value="MEDIUM">Medium</option>
            <option value="HIGH">High</option>
          </select>
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-primary" onClick={addUser}>Add User</button>
          ) : (
            <>
              <button className="btn-success" onClick={updateUser}>Update User</button>
              <button className="btn-secondary" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      <div className="fetch-section">
        <h3>Fetch User By ID</h3>
        <input type="number" value={idToFetch} onChange={(e) => setIdToFetch(e.target.value)} placeholder="Enter User ID" />
        <button className="btn-primary" onClick={getUserById}>Fetch</button>

        {fetchedUser && (
          <div className="fetched-user">
            <h4>User Found:</h4>
            <pre>{JSON.stringify(fetchedUser, null, 2)}</pre>
          </div>
        )}
      </div>

      <div className="table-section">
        <h3>All Users</h3>
        {users.length === 0 ? (
          <p>No users found.</p>
        ) : (
          <table>
            <thead>
              <tr>
                {Object.keys(user).map((key) => <th key={key}>{key}</th>)}
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map((usr) => (
                <tr key={usr.id}>
                  {Object.keys(user).map((key) => <td key={key}>{usr[key]}</td>)}
                  <td>
                    <button className="btn-success" onClick={() => handleEdit(usr)}>Edit</button>
                    <button className="btn-danger" onClick={() => deleteUser(usr.id)}>Delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

    </div>
  );
};

export default FitnessUserManager;
