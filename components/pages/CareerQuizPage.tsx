import React, { useState, useCallback } from 'react';
import * as api from '../../services/apiService';
import { CareerPath } from '../../types';
import Spinner from '../ui/Spinner';
import Card from '../ui/Card';

const CareerQuizPage: React.FC = () => {
    const [answers, setAnswers] = useState({ interests: '', activities: '', learningStyle: '', goal: '' });
    const [result, setResult] = useState<CareerPath | null>(null);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleSubmit = useCallback(async (e: React.FormEvent) => {
        e.preventDefault();
        setIsLoading(true);
        setError(null);
        setResult(null);
        try {
            const careerPath = await api.recommendCareerPath(answers);
            setResult(careerPath);
        } catch (err: any) {
            setError(err.message || 'Failed to get career recommendation.');
        } finally {
            setIsLoading(false);
        }
    }, [answers]);
    
    const isFormIncomplete = !answers.interests || !answers.activities || !answers.learningStyle || !answers.goal;

    return (
        <div className="max-w-4xl mx-auto">
            <h1 className="text-4xl font-bold text-center mb-2">AI Career Recommendation Engine</h1>
            <p className="text-center text-slate-600 dark:text-slate-300 mb-8">Answer a few questions to find the perfect tech career path for you.</p>

            {!result && (
                <Card className="p-8">
                    <form onSubmit={handleSubmit} className="space-y-6">
                        <div>
                            <label className="block text-sm font-medium mb-1">What are your interests? (e.g., problem-solving, design, data)</label>
                            <input type="text" name="interests" value={answers.interests} onChange={e => setAnswers({...answers, interests: e.target.value})} required className="w-full p-2 bg-white dark:bg-slate-700 border rounded-md" />
                        </div>
                        <div>
                            <label className="block text-sm font-medium mb-1">What activities do you enjoy most?</label>
                            <select name="activities" value={answers.activities} onChange={e => setAnswers({...answers, activities: e.target.value})} required className="w-full p-2 bg-white dark:bg-slate-700 border rounded-md">
                                <option value="">Select an option</option>
                                <option value="Building applications from scratch">Building applications from scratch</option>
                                <option value="Analyzing data and finding patterns">Analyzing data and finding patterns</option>
                                <option value="Designing user interfaces and experiences">Designing user interfaces and experiences</option>
                                <option value="Automating processes and managing infrastructure">Automating processes and managing infrastructure</option>
                            </select>
                        </div>
                         <div>
                            <label className="block text-sm font-medium mb-1">How do you prefer to learn?</label>
                            <select name="learningStyle" value={answers.learningStyle} onChange={e => setAnswers({...answers, learningStyle: e.target.value})} required className="w-full p-2 bg-white dark:bg-slate-700 border rounded-md">
                                <option value="">Select an option</option>
                                <option value="Hands-on projects and building things">Hands-on projects and building things</option>
                                <option value="Structured courses and theoretical knowledge">Structured courses and theoretical knowledge</option>
                                <option value="Reading documentation and articles">Reading documentation and articles</option>
                            </select>
                        </div>
                        <div>
                            <label className="block text-sm font-medium mb-1">What is your primary career goal?</label>
                             <input type="text" name="goal" value={answers.goal} onChange={e => setAnswers({...answers, goal: e.target.value})} required className="w-full p-2 bg-white dark:bg-slate-700 border rounded-md" placeholder="e.g., Get a high-paying job, build my own startup" />
                        </div>
                        <button type="submit" disabled={isFormIncomplete || isLoading} className="w-full px-6 py-3 text-lg font-semibold text-white bg-indigo-600 rounded-lg hover:bg-indigo-700 disabled:bg-indigo-400">
                            {isLoading ? <Spinner /> : 'Find My Career Path'}
                        </button>
                        {error && <p className="text-red-500 mt-4 text-center">{error}</p>}
                    </form>
                </Card>
            )}

            {result && (
                 <Card className="p-8">
                    <h2 className="text-3xl font-bold mb-4 text-center text-indigo-500">{result.name}</h2>
                    <p className="mb-6">{result.description}</p>
                    <div className="space-y-4">
                        <div><h3 className="font-bold text-lg">Learning Plan:</h3><ul className="list-disc ml-5">{result.learningPlan.map((item, i) => <li key={i}>{item}</li>)}</ul></div>
                        <div><h3 className="font-bold text-lg">Certifications:</h3><ul className="list-disc ml-5">{result.certifications.map((item, i) => <li key={i}>{item}</li>)}</ul></div>
                        <div><h3 className="font-bold text-lg">Job Portals:</h3><ul className="list-disc ml-5">{result.jobPortals.map((item, i) => <li key={i}>{item}</li>)}</ul></div>
                    </div>
                    <button onClick={() => setResult(null)} className="w-full mt-8 px-6 py-2 text-md font-semibold text-indigo-600 bg-indigo-100 rounded-lg hover:bg-indigo-200">
                        Take Quiz Again
                    </button>
                 </Card>
            )}
        </div>
    );
};

export default CareerQuizPage;